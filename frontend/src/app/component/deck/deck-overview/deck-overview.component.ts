import {Component, inject, OnInit, signal} from '@angular/core';
import {NgForOf} from "@angular/common";
import {DeckService} from "../../../service/deck.service";
import {RouterLink} from "@angular/router";
import {DeckOverview} from "../../../model/Deck";
import {DeckCreateComponent} from "../deck-create/deck-create.component";
import {BreadcrumbComponent} from "../../breadcrumb/breadcrumb.component";

@Component({
  selector: 'app-deck-overview',
    imports: [
        NgForOf,
        RouterLink,
        DeckCreateComponent,
        BreadcrumbComponent
    ],
  templateUrl: './deck-overview.component.html',
  styleUrl: './deck-overview.component.css'
})
export class DeckOverviewComponent implements OnInit {

  private deckService = inject(DeckService);
  protected deckOverviews = signal<DeckOverview[]>([]);

  ngOnInit() {
    this.getAllDecks();
  }

  private getAllDecks(): void {
    this.deckService.getAll().subscribe({
      next: (response: DeckOverview[]) => {
        this.deckOverviews.set(response);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }
}
