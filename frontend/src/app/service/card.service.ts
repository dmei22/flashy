import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Card} from "../model/Card";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080"

  constructor() { }

  // CREATE


  // READ
  public getAll(): Observable<Card[]> {
    return this.http.get<Card[]>(this.baseUrl + "/card/all");
  }

  // UPDATE


  // DELETE
}
