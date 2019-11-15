import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  signUpForm:FormGroup;
  formSubmitted:boolean
  error: string;
  passwordError: string;
  
  constructor(private userService:UserService, private router:Router) { }

  ngOnInit() {
    this.signUpForm=new FormGroup({
      'userName':new FormControl(null,[Validators.required,Validators.maxLength(20)],this.isUserNameTaken),
      'firstName':new FormControl(null,[Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(50)]),
      'lastName':new FormControl(null, [Validators.required,Validators.pattern('^[a-zA-Z]+$'),Validators.maxLength(50)]),
      'password':new FormControl(null,Validators.required),
      'confirmPassword':new FormControl(null,[Validators.required, this.isConfirmPasswordMatched.bind(this)])
    })
    console.log(this.signUpForm.get('userName'))
  }

  isUserNameTaken(formControl:FormControl):Promise<any>|Observable<any>{
    const promise=new Promise((resolve,reject)=>{
      setTimeout(()=>{
        if(formControl.value==='John')
        resolve({'userNameTaken':true})
        else
        resolve(null)
      },1000);
    })
    
    return promise
  }

  isConfirmPasswordMatched(formControl:FormControl):{[s:string]:boolean}{
    if(this.signUpForm)
    {
      if(formControl.value && formControl.value.length>0 && formControl.value!==this.signUpForm.get('password').value)
      return {'nomatch':true}
    }
    return null
  }

  onSubmitSignup()
  {
    let user1=this.signUpForm.value.password;
    let user2=this.signUpForm.value.confirmPassword;
    if(user1==user2){
      this.userService.addUser(this.signUpForm.value).subscribe((data)=>{
        this.router.navigate(['/login'])
      },
      error=>{
        this.error=error.error.message;
        console.log(this.error)
      })
    }
    else{
      this.passwordError="Password and Confirm Password are different"
    }
    // this.formSubmitted=true
    // this.signUpForm.reset();
  }

}
