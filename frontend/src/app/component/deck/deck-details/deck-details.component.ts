import {Component, inject, OnInit, signal} from '@angular/core';
import {DeckService} from "../../../service/deck.service";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {DeckDetails} from "../../../model/Deck";
import {NgForOf} from "@angular/common";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {CardCreateComponent} from "../../card/card-create/card-create.component";
import {BreadcrumbComponent} from "../../breadcrumb/breadcrumb.component";
import {ImageManagerComponent} from "../../image-manager/image-manager.component";
import {ImageService} from "../../../service/image.service";

@Component({
  selector: 'app-deck-details',
  imports: [
    NgForOf,
    ReactiveFormsModule,
    CardCreateComponent,
    RouterLink,
    BreadcrumbComponent,
    ImageManagerComponent,
  ],
  templateUrl: './deck-details.component.html',
  styleUrl: './deck-details.component.css'
})
export class DeckDetailsComponent implements OnInit {

  private deckService = inject(DeckService);
  protected imageService = inject(ImageService);
  private activatedRoute = inject(ActivatedRoute);
  private formBuilder = inject(FormBuilder);
  private route = inject(Router);

  protected deck = signal<DeckDetails | null>(null);
  protected deckEditForm!: FormGroup;

  public ngOnInit() {
    this.getDeckId();
  }

  protected getDeckId(): void {
    const id: number = Number(this.activatedRoute.snapshot.paramMap.get("id"));
    this.getDeckById(id);
  }

  protected onEdit(): void {
    document.getElementById("deck-edit-modal-close")?.click();

    this.deckService.update(this.deckEditForm.value).subscribe({
      next: (response: DeckDetails) => {
        this.deck.set(response);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  protected onDelete(): void {
    this.deckService.deleteById(this.deck()!.id).subscribe({
      next: response => {
        this.route.navigate(["/decks"])
      },
      error: error => {
        console.log(error);
      }
    })
  }

  // Helper methods
  private getDeckById(id: number): void {
    this.deckService.getById(id).subscribe({
      next: (response: DeckDetails) => {
        this.deck.set(response);
        this.buildDeckEditForm();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  private buildDeckEditForm(): void {
    this.deckEditForm = this.formBuilder.group({
      id: this.deck()?.id,
      name: this.deck()?.name,
      description: this.deck()?.description
    })
  }
}
