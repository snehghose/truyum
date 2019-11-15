import { Component, OnInit } from '@angular/core';
import { Cart } from './cart';
import { CartService } from './cart.service';
import { error } from 'util';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent {

  cart: Cart
  error: string;

  constructor(private cartService: CartService) {
    
    cartService.getAllCartItems().subscribe(data=>{
      this.cart=data
      console.log(data)
    })
    console.log(this.cart)
  }

  // onRemove(itemId:string){
  //   this.cartService.removeFromCart(itemId)
  // }

  onRemove(itemId:number){
    console.log("remove")
    this.cartService.removeCartItem(itemId).subscribe(()=>{
      this.cartService.getAllCartItems().subscribe((cart)=>{
        if(cart){
          console.log(cart);
          console.log("cart");
          this.cart=cart;
        }
      },
      (error)=>{
        this.cart=null;
        this.error=error.error.message;
      })
    })
  }
}
