import {inject, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ImageService {

  private http = inject(HttpClient);
  public readonly baseUrl = "http://localhost:8080"
  public readonly defaultImageUrl = "/assets/image/default_image.jpeg";
  private mapping!: string;

  constructor() {
  }

  public setMapping(entity: string, id: number): void {
    this.mapping = `${entity}/${id}`;
  }

  public getDeckImageUrl(deckId: number, version: number): string {
    const imageUrl =  `${this.baseUrl}/deck/${deckId}/image`;
    return version ? `${imageUrl}?v=${version}` : this.defaultImageUrl;
  }

  // CREATE
  public upload(formData: FormData): Observable<number> {
    return this.http.post<number>(this.getBaseImageUrl(), formData);
  }

  // READ


  // UPDATE
  public update(formData: FormData): Observable<number> {
    return this.http.put<number>(this.getBaseImageUrl(), formData);
  }

  // DELETE
  public delete(): Observable<void> {
    return this.http.delete<void>(this.getBaseImageUrl());
  }

  // Helper methods
  private getBaseImageUrl(): string {
    return `${this.baseUrl}/${this.mapping}/image`;
  }
}
