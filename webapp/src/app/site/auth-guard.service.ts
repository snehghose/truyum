import { CanActivate } from "@angular/router";
import { AuthService } from "./auth.service";
import { Router, ActivatedRouteSnapshot, RouterStateSnapshot } from "@angular/router";
import { Observable, Observer } from "rxjs";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn:'root'
})
export class AuthGuardService implements CanActivate{
    constructor(private authService:AuthService, private router:Router){}

    canActivate(route:ActivatedRouteSnapshot, state:RouterStateSnapshot):Observable<boolean>|Promise<boolean>|boolean{
        this.authService.redirectUrl=state.url

        console.log('URL',state.url)

        return Observable.create((observer:Observer<boolean>)=>{
            if(this.authService.loggedIn){
                observer.next(true)
            }else{
                this.router.navigate(['login'],{queryParams:{from:state.url.substr(1)}})
            }
        })
    }
}