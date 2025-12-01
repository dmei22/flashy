import {Component, inject, OnInit, signal} from '@angular/core';
import {CardService} from "../../../service/card.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CardDetails} from "../../../model/Card";
import {CardFaceComponent} from "./card-face/card-face.component";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {BreadcrumbComponent} from "../../breadcrumb/breadcrumb.component";

@Component({
  selector: 'app-card-details',
  imports: [
    CardFaceComponent,
    ReactiveFormsModule,
    BreadcrumbComponent
  ],
  templateUrl: './card-details.component.html',
  styleUrl: './card-details.component.css'
})
export class CardDetailsComponent implements OnInit {

  private cardService = inject(CardService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  protected cardDetails = signal<CardDetails | null>(null);
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
      next: (response: CardDetails) => {
        console.log("Retrieved Card with id: " + response.id);
        this.cardDetails.set(response);
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
      cardId: this.cardDetails()?.id,
      front: this.cardDetails()?.front,
      back: this.cardDetails()?.back,
    });
  }

  protected onUpdate(): void {
    document.getElementById("card-update-modal-close")?.click();

    this.cardService.update(this.cardUpdateForm.value).subscribe({
      next: (response: CardDetails) => {
        console.log("Update Card success");
        this.cardDetails.set(response);
      },
      error: (error) => {
        console.log("Failed to update Card");
        console.log(error);
      }
    })
  }

  protected onDelete(): void {
    this.cardService.deleteById(this.cardDetails()!.id).subscribe({
      next: () => {
        console.log("Delete Card success");
        this.router.navigate(["/deck", this.cardDetails()?.deckId]);
      },
      error: (error) => {
        console.log("Failed to delete Card");
        console.log(error);
      }
    });
  }
}