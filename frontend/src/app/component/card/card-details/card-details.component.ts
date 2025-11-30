import {Component, inject, OnInit, signal} from '@angular/core';
import {CardService} from "../../../service/card.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Card} from "../../../model/Card";
import {CardFaceComponent} from "./card-face/card-face.component";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-card-details',
  imports: [
    CardFaceComponent,
    ReactiveFormsModule
  ],
  templateUrl: './card-details.component.html',
  styleUrl: './card-details.component.css'
})
export class CardDetailsComponent implements OnInit {

  private cardService = inject(CardService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  protected card = signal<Card | null>(null);
  protected cardUpdateForm!: FormGroup;

  ngOnInit() {
    this.getCardId();
  }

  private getCardId(): void {
    const cardId = Number(this.activatedRoute.snapshot.paramMap.get("id"));
    this.getCard(cardId);
  }

  private getCard(id: number): void {
    this.cardService.getById(id).subscribe({
      next: (response: Card) => {
        console.log("Retrieved Card with id: " + response.id);
        this.card.set(response);
        this.buildCardUpdateForm();
      },
      error: (error) => {
        console.log("Failed to retrieve Card");
        console.log(error);
      }
    });
  }

  private buildCardUpdateForm(): void {
    this.cardUpdateForm = this.formBuilder.group({
      cardId: this.card()?.id,
      front: this.card()?.front,
      back: this.card()?.back,
    });
  }

  protected onUpdate(): void {
    document.getElementById("card-update-modal-close")?.click();

    this.cardService.update(this.cardUpdateForm.value).subscribe({
      next: (response: Card) => {
        console.log("Update Card success");
        this.card.set(response);
      },
      error: (error) => {
        console.log("Failed to update Card");
        console.log(error);
      }
    })
  }

  protected onDelete(): void {
    this.cardService.deleteById(this.card()!.id).subscribe({
      next: () => {
        console.log("Delete Card success");
        this.router.navigate(["/decks"]);
      },
      error: (error) => {
        console.log("Failed to delete Card");
        console.log(error);
      }
    });
  }
}