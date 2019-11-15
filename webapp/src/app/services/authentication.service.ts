import { Injectable } from "@angular/core";
import { environment } from 'src/environments/environment';
import { Observable } from "rxjs";
import { HttpHeaders, HttpClient } from "@angular/common/http";

@Injectable({
    providedIn: 'root'
})
export class AuthenticationService {
    baseUrl = environment.baseUrl;
    constructor(private httpClient: HttpClient) { }
    public authenticate(user: string, password: string): Observable<any> {
        let credentials = btoa(user + ':' + password);
        let headers = new HttpHeaders();
        headers = headers.set('Authorization', 'Basic ' + credentials);
        console.log({ headers });
        return this.httpClient.get(this.baseUrl + "/authenticate", { headers })
    }
}