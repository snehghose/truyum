import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';
import { CartService } from 'src/app/shopping/cart/cart.service';
import { UserAuthService } from 'src/app/services/user-auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent {

  constructor(private router:Router,private cartService:CartService, private authService:AuthService, private userAuthService:UserAuthService) { }

  isAuthenticated(){
    return this.authService.loggedIn
  }

  isAdmin(){
    return this.authService.isAdmin
  }

  getUser(){
    return this.userAuthService.getUser()
  }

  onSignOut(){
    this.cartService.clearCart()
    this.authService.logOut()
    this.router.navigate([this.authService.redirectUrl])
  }
}
