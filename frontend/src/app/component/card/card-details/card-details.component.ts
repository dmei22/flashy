import {Component, inject, OnInit, signal} from '@angular/core';
import {CardService} from "../../../service/card.service";
import {ActivatedRoute, Router} from "@angular/router";
import {CardDetails} from "../../../model/Card";
import {CardFaceComponent} from "./card-face/card-face.component";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {BreadcrumbComponent} from "../../breadcrumb/breadcrumb.component";
import {DeckService} from "../../../service/deck.service";

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
  private deckService = inject(DeckService);
  private activatedRoute = inject(ActivatedRoute);
  private router = inject(Router);
  private formBuilder = inject(FormBuilder);

  protected deckId = signal<number | null>(null);
  protected cardId = signal<number | null>(null);
  protected cardDetails = signal<CardDetails | null>(null);
  protected cardUpdateForm!: FormGroup;

  ngOnInit() {
    this.getParams();
    this.getCard(this.cardId()!);
  }

  private getParams(): void {
    this.cardId.set(Number(this.activatedRoute.snapshot.paramMap.get("cardId")));
    this.deckId.set(Number(this.activatedRoute.snapshot.paramMap.get("deckId")));
  }

  private getCard(id: number): void {
    this.cardService.getCard(id).subscribe({
      next: (response: CardDetails) => {
        this.cardDetails.set(response);
        this.buildCardUpdateForm();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  private buildCardUpdateForm(): void {
    this.cardUpdateForm = this.formBuilder.group({
      front: this.cardDetails()?.front,
      back: this.cardDetails()?.back,
    });
  }

  protected onUpdate(): void {
    document.getElementById("card-update-modal-close")?.click();

    this.cardService.update(this.cardId()!, this.cardUpdateForm.value).subscribe({
      next: (response: CardDetails) => {
        this.cardDetails.set(response);
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  protected onDelete(): void {
    this.deckService.deleteCard(this.deckId()!, this.cardDetails()!.id).subscribe({
      next: () => {
        this.router.navigate(["/deck", this.deckId()]);
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}