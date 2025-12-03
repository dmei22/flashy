import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {DeckCreateRequest, DeckDetails, DeckOverview, DeckUpdateRequest} from "../model/Deck";

@Injectable({
  providedIn: 'root'
})
export class DeckService {

  private http = inject(HttpClient);
  private baseUrl = "http://localhost:8080"

  constructor() { }

  // CREATE
  public create(request: DeckCreateRequest): Observable<DeckOverview> {
    return this.http.post<DeckOverview>(`${this.baseUrl}/deck/create`, request).pipe(
      map((response: DeckOverview) => Object.assign(new DeckDetails(), response))
    );
  }

  // READ
  public getAll(): Observable<DeckOverview[]> {
    return this.http.get<DeckOverview[]>(`${this.baseUrl}/deck/all`).pipe(
        map((response: DeckOverview[]) => response.map(
            deckOverview => Object.assign(new DeckOverview(), deckOverview))
        )
    );
  }

  public getById(id: number): Observable<DeckDetails> {
    return this.http.get<DeckDetails>(`${this.baseUrl}/deck/find/${id}`).pipe(
        map((response: DeckDetails) => Object.assign(new DeckDetails(), response))
    );
  }

  // UPDATE
  public update(request: DeckUpdateRequest): Observable<DeckDetails> {
      return this.http.put<DeckDetails>(`${this.baseUrl}/deck/update`, request).pipe(
          map((response: DeckDetails) => Object.assign(new DeckDetails(), response))
      );
  }

  // DELETE
  public deleteById(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/deck/delete/${id}`);
  }
}
