import {Component, inject, OnInit, signal} from '@angular/core';
import {NgForOf} from "@angular/common";
import {DeckService} from "../../../service/deck.service";
import {RouterLink} from "@angular/router";
import {DeckOverview} from "../../../model/Deck";
import {DefaultDeckOverviews} from "../../../resource/DefaultDecks";
import {DeckCreateComponent} from "../deck-create/deck-create.component";

@Component({
  selector: 'app-deck-overview',
    imports: [
        NgForOf,
        RouterLink,
        DeckCreateComponent
    ],
  templateUrl: './deck-overview.component.html',
  styleUrl: './deck-overview.component.css'
})
export class DeckOverviewComponent implements OnInit {

  private deckService = inject(DeckService);
  protected decks = signal<DeckOverview[]>([]);

  ngOnInit() {
    this.getAllDecks();
  }

  protected getAllDecks(): void {
    this.deckService.getAll().subscribe({
      next: (response: DeckOverview[]) => {
        console.log("Retrieved all Decks");
        this.decks.set(response);
      },
      error: (error) => {
        console.log("Failed to retrieve all Decks, loading default Decks.");
        console.log(error);
        this.decks.set(DefaultDeckOverviews);
      }
    })
  }
}
