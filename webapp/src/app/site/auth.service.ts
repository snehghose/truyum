import { User } from "./user.model"
import { UserService } from "./user.service"
import { Injectable } from "@angular/core"
import { UserAuthService } from "../services/user-auth.service"
import { AuthenticationService } from "../services/authentication.service"
import { Router } from "@angular/router"
import { CartService } from "../shopping/cart/cart.service"

@Injectable({
    providedIn: 'root'
})

export class AuthService {

    loggedIn = false
    isLoginValid: boolean = true
    isAdmin = false
    accessToken: string
    redirectUrl = '/'
    userAuthenticated: User

    constructor(private userService: UserService, private userAuthService: UserAuthService, private authenticationService: AuthenticationService,private router: Router, private cartService:CartService) { }

    logIn(username: string, password: string) {
        // this.userService.authenticate(username, password).subscribe((user: User) => {
        //     if (user) {
        //         this.loggedIn = true
        //         this.userAuthenticated = user
        //         this.isAdmin = user.role === 'Admin'
        //     }
        // })

        this.authenticationService.authenticate(username, password).subscribe(data => {
            this.userAuthService.loggedIn = true
            console.log(data.role);
            this.userAuthService.setToken(data.token)
            this.userAuthService.setRole(data.role)
            this.userAuthService.setUser(username)
            this.loggedIn=true
            this.isLoginValid=true
            //this.userAuthenticated=user
            this.isAdmin=data.role==='ROLE_ADMIN'
            
            if (this.userAuthService.getUser() != "admin" && this.userAuthService.getMenuItemId() != -1) {
                this.cartService.addCartItem(this.userAuthService.getUser(),this.userAuthService.getMenuItemId()).subscribe(data=>{
                  console.log("Added");
                })
            }
            this.router.navigate(['menu'])

        },(error)=>{
            this.isLoginValid=false
            this.loggedIn=false
            this.userAuthService.loggedIn=false
        })
    }

    logOut() {
        this.redirectUrl = '/'
        this.loggedIn = false
    }

    isAdminUser() {
        return this.isAdmin
    }
}