import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {DeckCreateRequest, DeckDetails, DeckOverview} from "../model/Deck";

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080"

  constructor() { }

  // CREATE
  public createDeck(request: DeckCreateRequest): Observable<DeckOverview> {
    return this.http.post<DeckOverview>(`${this.baseUrl}/deck/create`, request);
  }

  // READ
  public getAll(): Observable<DeckOverview[]> {
    return this.http.get<DeckOverview[]>(`${this.baseUrl}/deck/all`);
  }

  public getById(id: number): Observable<DeckDetails> {
    return this.http.get<DeckDetails>(`${this.baseUrl}/deck/find/${id}`);
  }

  // UPDATE


  // DELETE
  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deck/delete/${id}`);
  }
}
