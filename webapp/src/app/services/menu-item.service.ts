import { Injectable } from "@angular/core";
import { Subject, Observable } from "rxjs";
import { environment } from "src/environments/environment"
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { UserAuthService } from "./user-auth.service";
import { MenuItem } from "../food/food.model";

@Injectable({
    providedIn: 'root'
})
export class MenuItemService {
    filter = new Subject()
    baseUrl = environment.baseUrl

    constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) { }

    getAllMenuItems(): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.userAuthService.getToken()
            })
        }
        console.log(this.userAuthService.getRole());
        console.log(this.userAuthService.getToken());
        
        
        return this.httpClient.get<MenuItem[]>(this.baseUrl + "/menu-items", httpOptions)
    }

    getMenuItem(menuItemId: number): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.userAuthService.getToken()
            })
        }
        return this.httpClient.get<MenuItem[]>(`${this.baseUrl + "/menu-items"}/${menuItemId}`, httpOptions)
    }

    updateMenuItem(menuItem: MenuItem): Observable<any> {
        const httpOptions = {
            headers: new HttpHeaders({
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + this.userAuthService.getToken()
            })
        }
        console.log("duh");
        
        return this.httpClient.put<void>(this.baseUrl+"/menu-items",menuItem,httpOptions)
    }
}