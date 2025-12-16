import {Component, computed, inject, OnInit, signal} from '@angular/core';
import {CardDetails} from "../../model/Card";
import {CardService} from "../../service/card.service";
import {DeckService} from "../../service/deck.service";
import {ActivatedRoute, Router} from "@angular/router";
import {ReviewCreateRequest} from "../../model/Review";
import {concatMap, map, Observable, of, tap} from "rxjs";

@Component({
  selector: 'app-review',
  imports: [],
  templateUrl: './review.component.html',
  styleUrl: './review.component.css'
})
export class ReviewComponent implements OnInit {

  private cardService = inject(CardService);
  private deckService = inject(DeckService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);

  protected deckId = signal<number | null>(null);
  protected cardDetailsDue = signal<CardDetails[] | null>(null);
  private currentCardIndex = signal(0);
  protected currentCard = computed<CardDetails | null>(() => {
    const cards = this.cardDetailsDue();
    const index = this.currentCardIndex();

    return cards?.at(index) ?? null;
  });

  constructor() {
  }

  public ngOnInit() {
    this.initFlow().subscribe({
      next: (response) => console.log("Initialize flow complete"),
      error: (error) => console.log(error)
    });
  }

  protected onNext(cardId: number, success: boolean): void {
    if (this.finished()) {
      this.router.navigate(["/deck", this.deckId()]);
    }

    this.currentCardIndex.set(this.currentCardIndex() + 1);
    const request: ReviewCreateRequest = { success };
    this.cardService.createReview(cardId, request).subscribe({
      next: (response: void) => {
      },
      error: (error) => {
        console.log(error)
      }
    });
  }

  // Helper methods
  private initFlow(): Observable<void> {
    return of(null).pipe(
        tap(() => this.getParams()),
        concatMap(() => this.getCardsDue()),
        tap(cardDetails => this.cardDetailsDue.set(cardDetails)),
        map(() => void 0)
    );
  }

  private getParams(): void {
    this.deckId.set(Number(this.activatedRoute.snapshot.paramMap.get("deckId")));
  }

  private getCardsDue(): Observable<CardDetails[]> {
    return this.deckService.getCardsDue(this.deckId()!);
  }

  private finished(): boolean {
    return this.currentCardIndex() >= this.cardDetailsDue.length;
  }
}
