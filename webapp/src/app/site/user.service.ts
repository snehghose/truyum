import { Observable, Observer } from "rxjs";
import { User } from "./user.model";
import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    baseUrl=environment.baseUrl;

    constructor(private httpClient:HttpClient) { }
    
    authenticate(username: string, password: string): Observable<User> {
        return Observable.create((observer: Observer<any>) => {
            if (username !== 'admin') {
                observer.next({ username, firstName: 'John', lastName: 'Doe', role: 'Customer', accessToken:'JWT-TOKEN' })
            } else {
                observer.next({ username, firstName: 'Admin', lastName: 'User', role: 'Admin', accessToken:'JWT-TOKEN' })
            }
            return null
        })
    }

    addUser(user:User){
        return this.httpClient.post(this.baseUrl+"/users",user)
    }
}