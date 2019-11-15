import { Injectable } from "@angular/core";
import { FoodService } from "src/app/food/food.service";
import { Cart } from "./cart";
import { MenuItem } from "src/app/food/food.model";
import { UUID } from 'angular2-uuid';
import { UserAuthService } from "src/app/services/user-auth.service";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class CartService {

    cart: Cart = {
        menuItemList: null,
        total: 0
    }
    user: string;
    baseUrl=environment.baseUrl;

    constructor(private foodService: FoodService, private userAuthService:UserAuthService, private httpClient:HttpClient) { }

    getAllCartItems():Observable<any>{
        console.log("Inside get all cart items");
        this.user=this.userAuthService.getUser();
        const httpOptions={
            headers:new HttpHeaders({
                'Content-Type':'application/json',
                'Authorization':'Bearer '+this.userAuthService.getToken()
            })
        }
        this.httpClient.get<Cart>(this.baseUrl+"/carts"+"/"+this.user,httpOptions).subscribe((data)=>{
            console.log("data"+data)
        });
        
        return this.httpClient.get<Cart>(this.baseUrl+"/carts"+"/"+this.user,httpOptions)
    }

    addCartItem(user:string,menuItemId:number):Observable<any>{
        console.log("Inside add cart item");
        console.log(user);
        console.log(this.userAuthService.getToken());
        const httpOptions={
            headers:new HttpHeaders({
                'Content-Type':'application/json',
                'Authorization':'Bearer '+this.userAuthService.getToken()
            })
        }
        return this.httpClient.post<void>(this.baseUrl+"/carts"+"/"+user+"/"+menuItemId,{},httpOptions)
    }

    removeCartItem(menuItemId:number){
        console.log("Inside remove cart item");
        this.user=this.userAuthService.getUser()
        const httpOptions={
            headers:new HttpHeaders({
                'Content-Type':'application/json',
                'Authorization':'Bearer '+this.userAuthService.getToken()
            })
        }
        return this.httpClient.delete<Cart>(this.baseUrl+"/carts"+"/"+this.user+"/"+menuItemId,httpOptions)
    }
    getCart() {

        return this.cart
    }

    // addToCart(productId: number, quantity: number) {
    //     this.foodService.getItem(productId).subscribe((productToBeAdded: MenuItem) => {
    //         const uid = UUID.UUID()
    //         if (this.cart.items === null) {
    //             this.cart.items = [{ itemId: uid, product: productToBeAdded, quantity }]
    //             this.cart.total = productToBeAdded.price
    //         } else {
    //             const index = this.cart.items.findIndex(p => p.product.id === productId)
    //             if (index == -1)
    //                 this.cart.items.push({ itemId: uid, product: productToBeAdded, quantity })
    //             else
    //                 this.cart.items[index].quantity += 1
    //             this.cart.total += productToBeAdded.price
    //         }
    //     })
    // }

    // removeFromCart(itemId: string) {
    //     const itemIndex = this.cart.items.findIndex(cartItem => cartItem.itemId === itemId);
    //     if (this.cart.items[itemIndex].quantity > 1) {
    //         this.cart.items[itemIndex].quantity -= 1
    //         this.cart.total -= this.cart.items[itemIndex].product.price
    //     }
    //     else {
    //         const itemToBeRemoved = this.cart.items.splice(itemIndex, 1)[0];
    //         this.cart.total -= itemToBeRemoved.product.price;
    //     }
    // }

    clearCart() {
        this.cart.menuItemList = null
        this.cart.total = 0
    }
}