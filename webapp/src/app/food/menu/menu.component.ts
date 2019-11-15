import { Component, OnInit } from '@angular/core';
import { MenuItem } from '../food.model';
import { FoodService } from '../food.service';
import { CartService } from 'src/app/shopping/cart/cart.service';
import { AuthService } from 'src/app/site/auth.service';
import { Router } from '@angular/router';
import { MenuItemService } from 'src/app/services/menu-item.service';
import { UserAuthService } from 'src/app/services/user-auth.service';

@Component({
    selector: 'app-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
    menuItems: MenuItem[]
    originalList: MenuItem[]
    user: string

    constructor(private foodService: FoodService, private cartService: CartService, private menuItemService: MenuItemService, 
        private authService: AuthService, private router: Router, private userAuthService:UserAuthService) { }

    ngOnInit() {
        // this.foodService.getItems().subscribe((data: MenuItem[]) => {
        //     this.originalList = [...data]
        //     this.menuItems = [...data]
        // })

        // this.foodService.filter.subscribe((obj: { title: string }) => {
        //     if (obj.title !== '') {
        //         const result = this.originalList.filter(item => item.itemName.toLowerCase().includes(obj.title.toLowerCase()))
        //         this.menuItems = result ? result : []
        //     } else {
        //         this.menuItems = [...this.originalList]
        //     }
        // })
        this.menuItemService.getAllMenuItems().subscribe((data: MenuItem[]) => {
            this.originalList = [...data]
            this.menuItems = [...data]
            console.log(this.menuItems);
            
        })
        this.menuItemService.filter.subscribe((obj: { title: string }) => {
            if (obj.title !== '') {
                const result = this.originalList.filter(food => food.itemName.toLowerCase().includes(obj.title.toLowerCase()))
                this.menuItems = result ? result : []
            } else {
                this.menuItems = [...this.originalList]
            }
        })
    }

    addItemToCart(productId: number) {
        
        if (!this.authService.loggedIn) {
            this.router.navigate(['/login'], { queryParams: { notLogged: true } })
        } else {
            this.user=this.userAuthService.getUser()
        console.log("add food"+this.user);
        
        this.cartService.addCartItem(this.user,productId).subscribe((data)=>{
            console.log("Item added");
            
        });
            //this.cartService.addToCart(productId, 1)
        }
    }
    isAdmin(){
        return this.authService.isAdmin;
    }
}
