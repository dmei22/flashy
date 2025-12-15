import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {DeckCreateRequest, DeckDetails, DeckOverview, DeckUpdateRequest} from "../model/Deck";
import {CardCreateRequest, CardOverview} from "../model/Card";

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  private http = inject(HttpClient);
  private requestMapping = "http://localhost:8080/deck"

  constructor() { }

  // CREATE
  public createDeck(request: DeckCreateRequest): Observable<DeckOverview> {
    return this.http.post<DeckOverview>(`${this.requestMapping}`, request).pipe(
      map((response: DeckOverview) => Object.assign(new DeckDetails(), response))
    );
  }

  public createCard(deckId: number, request: CardCreateRequest): Observable<CardOverview> {
    return this.http.post<CardOverview>(`${this.requestMapping}/${deckId}/card`, request);
  }

  // READ
  public getDecks(): Observable<DeckOverview[]> {
    return this.http.get<DeckOverview[]>(`${this.requestMapping}/all`).pipe(
        map((response: DeckOverview[]) => response.map(
            deckOverview => Object.assign(new DeckOverview(), deckOverview))
        )
    );
  }

  public getDeck(deckId: number): Observable<DeckDetails> {
    return this.http.get<DeckDetails>(`${this.requestMapping}/${deckId}`).pipe(
        map((response: DeckDetails) => Object.assign(new DeckDetails(), response))
    );
  }

  // UPDATE
  public updateDeck(request: DeckUpdateRequest): Observable<DeckDetails> {
      return this.http.put<DeckDetails>(`${this.requestMapping}`, request).pipe(
          map((response: DeckDetails) => Object.assign(new DeckDetails(), response))
      );
  }

  // DELETE
  public deleteDeck(deckId: number): Observable<void> {
    return this.http.delete<void>(`${this.requestMapping}/${deckId}`);
  }

  public deleteCard(deckId: number, id: number): Observable<void> {
    return this.http.delete<void>(`${this.requestMapping}/${deckId}/card/${id}`);
  }
}
