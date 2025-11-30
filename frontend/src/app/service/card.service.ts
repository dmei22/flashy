import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Card, CardCreateRequest, CardOverview, CardUpdateRequest} from "../model/Card";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080"

  constructor() { }

  // CREATE
  public create(request: CardCreateRequest): Observable<CardOverview> {
    return this.http.post<CardOverview>(`${this.baseUrl}/card/create`, request);
  }

  // READ
  public getAll(): Observable<Card[]> {
    return this.http.get<Card[]>(this.baseUrl + "/card/all");
  }

  public getById(id: number): Observable<Card> {
    return this.http.get<Card>(`${this.baseUrl}/card/find/${id}`);
  }

  // UPDATE
  public update(request: CardUpdateRequest): Observable<Card> {
    return this.http.put<Card>(`${this.baseUrl}/card/update`, request);
  }

  // DELETE
  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/card/delete/${id}`);
  }
}
