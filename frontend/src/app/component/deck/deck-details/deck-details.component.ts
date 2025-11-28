import {Component, inject, OnInit, signal} from '@angular/core';
import {DeckService} from "../../../service/deck.service";
import {ActivatedRoute} from "@angular/router";
import {defaultDeck} from "../../../resource/DefaultDecks";
import {DeckDetails} from "../../../model/Deck";
import {NgForOf} from "@angular/common";

@Component({
  selector: 'app-deck-details',
    imports: [
        NgForOf
    ],
  templateUrl: './deck-details.component.html',
  styleUrl: './deck-details.component.css'
})
export class DeckDetailsComponent implements OnInit {

  private deckService = inject(DeckService);
  private router = inject(ActivatedRoute);
  protected deck = signal<DeckDetails | null>(null);

  ngOnInit() {
    this.getDeckId();
  }

  private getDeckId(): void {
    const id: number = Number(this.router.snapshot.paramMap.get("id"));
    this.getDeckById(id);
  }

  private getDeckById(id: number): void {
    this.deckService.getById(id).subscribe({
      next: (response: DeckDetails) => {
        console.log("Retrieved Deck with id " + id);
        this.deck.set(response);
    },
      error: (error) => {
        console.log("Failed to retrieve Deck with id " + id);
        console.log("Setting random Default Deck");
        this.deck.set(defaultDeck());
        console.log(error);
      }
    });
  }
}
