import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable, Subject, Observer } from "rxjs";
import { MenuItem } from "./food.model";

@Injectable({
    providedIn:'root'
})
export class FoodService{
    
    configUrl:string='assets/menu-items.json'
    filter= new Subject()

    constructor(private http:HttpClient){}
    getItems():Observable<any>{
        return this.http.get(this.configUrl)
    }

    getItem(id:number):Observable<any>{
        return Observable.create((observer:Observer<MenuItem>)=>{
            this.getItems().subscribe((products:MenuItem[])=>{
                const prod=products.find(product=>product.id==id)
                observer.next(prod)
            })
        })
    }
}