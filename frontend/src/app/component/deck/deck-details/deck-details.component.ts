import {Component, inject, OnInit, signal} from '@angular/core';
import {DeckService} from "../../../service/deck.service";
import {ActivatedRoute, Router} from "@angular/router";
import {defaultDeck} from "../../../resource/DefaultDecks";
import {DeckDetails} from "../../../model/Deck";
import {NgForOf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-deck-details',
  imports: [
    NgForOf,
    ReactiveFormsModule,
  ],
  templateUrl: './deck-details.component.html',
  styleUrl: './deck-details.component.css'
})
export class DeckDetailsComponent implements OnInit {

  private deckService = inject(DeckService);
  private activatedRoute = inject(ActivatedRoute);
  private formBuilder = inject(FormBuilder);
  private route = inject(Router);

  protected deck = signal<DeckDetails | null>(null);
  protected deckEditForm!: FormGroup;

  ngOnInit() {
    this.getDeckId();
  }

  private getDeckId(): void {
    const id: number = Number(this.activatedRoute.snapshot.paramMap.get("id"));
    this.getDeckById(id);
  }

  private buildDeckEditForm(): void {
    this.deckEditForm = this.formBuilder.group({
      id: this.deck()?.id,
      name: this.deck()?.name,
      description: this.deck()?.description
    })
  }

  private getDeckById(id: number): void {
    this.deckService.getById(id).subscribe({
      next: (response: DeckDetails) => {
        console.log("Retrieved Deck with id " + id);
        this.deck.set(response);
        this.buildDeckEditForm();
      },
      error: (error) => {
        console.log("Failed to retrieve Deck with id " + id);
        console.log("Setting random Default Deck");
        this.deck.set(defaultDeck());
        this.buildDeckEditForm();
        console.log(error);
      }
    });
  }

  protected onEdit(): void {
    document.getElementById("deck-edit-modal-close")?.click();

    this.deckService.update(this.deckEditForm.value).subscribe({
      next: (response: DeckDetails) => {
        console.log("Update Deck success");
        this.deck.set(response);
      },
      error: (error) => {
        console.log("Failed to update Deck");
        console.log(error);
      }
    })
  }

  protected onDelete(): void {
    this.deckService.deleteById(this.deck()!.id).subscribe({
      next: response => {
        console.log("Deleted Deck");
        this.route.navigate(["/decks"])
      },
      error: error => {
        console.log("Failed to delete Deck");
        console.log(error);
      }
    })
  }
}
