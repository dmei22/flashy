import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CardCreateRequest, CardDetails, CardOverview, CardUpdateRequest} from "../model/Card";
import {ReviewCreateRequest} from "../model/Review";

@Injectable({
  providedIn: 'root'
})
export class CardService {

  private http = inject(HttpClient);
  private requestMapping = "http://localhost:8080/card"

  constructor() { }

  // CREATE
  public createReview(cardId: number, request: ReviewCreateRequest): Observable<void> {
    return this.http.post<void>(`${this.requestMapping}/review`, request);
  }

  // READ
  public getCard(cardId: number): Observable<CardDetails> {
    return this.http.get<CardDetails>(`${this.requestMapping}/${cardId}`);
  }

  // UPDATE
  public update(cardId: number, request: CardUpdateRequest): Observable<CardDetails> {
    return this.http.put<CardDetails>(`${this.requestMapping}/${cardId}`, request);
  }

  // DELETE

}
