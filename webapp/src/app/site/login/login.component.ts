import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AuthService } from '../auth.service';
import { UserAuthService } from 'src/app/services/user-auth.service';
import { AuthenticationService } from 'src/app/services/authentication.service';
import { CartService } from 'src/app/shopping/cart/cart.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  //isLoginValid: boolean = true
  authSource: boolean = false
  notLogged: boolean = false;
  error:string
  constructor(private router: Router, private route: ActivatedRoute, private userAuthService: UserAuthService, private authenticationService: AuthenticationService, private authService: AuthService, private cartService:CartService) {
    this.notLogged = route.snapshot.queryParams['notLogged'];
  }

  ngOnInit() {
    console.log(this.userAuthService.loggedIn);
    //this.isLoginValid=this.authService.isLoginValid
    
    this.route.queryParams.subscribe((params: Params) => {
      this.authSource = params['from']
    })
    this.authSource = this.route.snapshot.queryParams['notLogged']
  }

  onSubmit(form: NgForm) {
      this.authService.logIn(form.value.username,form.value.password)
    //   this.authenticationService.authenticate(form.value.username,form.value.password).subscribe(data=>{
    //   this.userAuthService.loggedIn=true
    //   console.log(data.token);
    //   this.userAuthService.setToken(data.token)
    //   this.userAuthService.setRole(data.role)
    //   this.userAuthService.setUser(form.value.username)
    //   if(this.userAuthService.getUser()!="admin" && this.userAuthService.getMenuItemId()!=-1){
    //     // this.cartService.addCartItem(this.userAuthService.getUser(),this.userAuthService.getMenuItemId()).subscribe(data=>{
    //     //   console.log("Added");
    //     // })
    //   }
    //   this.router.navigate(['menu'])
    // },
    // (error)=>{
    //   console.log(error.message);
    //   if(error.status==401){
    //     this.error="Invalid username/password"
    //   }
    // })
    // const username = form.value.username
    // const password = form.value.password
    // if (username === 'John') {
    //   this.isLoginValid = false
    // } else {

    //   this.authService.logIn(username, password)
    //   this.router.navigate([this.authService.redirectUrl])
    // }
  }
  isLoginValid()
  {
    return this.authService.isLoginValid;
  }

}
