import {Component, inject, OnInit, signal} from '@angular/core';
import {CardService} from "../../../service/card.service";
import {ActivatedRoute} from "@angular/router";
import {Card} from "../../../model/Card";

@Component({
  selector: 'app-card-details',
  imports: [],
  templateUrl: './card-details.component.html',
  styleUrl: './card-details.component.css'
})
export class CardDetailsComponent implements OnInit {

  private cardService = inject(CardService);
  private activatedRoute = inject(ActivatedRoute);

  protected card = signal<Card | null>(null);

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
      },
      error: (error) => {
        console.log("Failed to retrieve Card");
        console.log(error);
      }
    });
  }
}
