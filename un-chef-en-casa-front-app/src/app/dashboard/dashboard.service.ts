import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Dashboard } from './dashboard';

@Injectable()
export class DashboardService {

  private url: string="http://localhost:8081/uber/v1.2/requests/current";

  private httpHeaders=new HttpHeaders({'Content-Type':'application/json'});

  constructor(private http: HttpClient) { }

  public getDatos():Observable<Dashboard>{
    this.url="http://localhost:8082/ecomove/v0.1/dashboard/";
    let id=JSON.parse(sessionStorage.getItem("usuariologueado")).id;
    return this.http.get<Dashboard>(`${this.url}/${id}`,{headers:this.httpHeaders});
  }


}
