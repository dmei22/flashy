import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DeckDetails, DeckOverview} from "../model/Deck";

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080"

  constructor() { }

  // CREATE


  // READ
  public getAll(): Observable<DeckOverview[]> {
    return this.http.get<DeckOverview[]>(`${this.baseUrl}/deck/all`);
  }

  public getById(id: number): Observable<DeckDetails> {
    return this.http.get<DeckDetails>(`${this.baseUrl}/deck/find/${id}`);
  }

  // UPDATE


  // DELETE
}
