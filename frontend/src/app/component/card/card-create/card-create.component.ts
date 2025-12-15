import {Component, inject, input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {CardService} from "../../../service/card.service";
import {CardOverview} from "../../../model/Card";
import {Router} from "@angular/router";
import {DeckService} from "../../../service/deck.service";

@Component({
  selector: 'app-card-create',
  imports: [
    ReactiveFormsModule,
  ],
  templateUrl: './card-create.component.html',
  styleUrl: './card-create.component.css'
})
export class CardCreateComponent implements OnInit {

  private deckService = inject(DeckService);
  private formBuilder = inject(FormBuilder);
  private router = inject(Router);

  protected cardCreateForm!: FormGroup;
  public deckId = input.required<number>();

  public ngOnInit() {
    this.buildCardCreateForm();
  }

  protected buildCardCreateForm(): void {
    this.cardCreateForm = this.formBuilder.group({
      deckId: this.deckId(),
      front: "",
      back: "",
    });
  }

  protected onCreate(): void {
    document.getElementById("card-create-modal-close")?.click();
    console.log("Sending Card create request:");
    console.log(this.cardCreateForm.value);

    this.deckService.createCard(this.deckId(), this.cardCreateForm.value).subscribe({
      next: (response: CardOverview) => {
        console.log("Create Card success");
        this.router.navigate(["deck", this.deckId(), "card", response.id]);
      },
      error: (error) => {
        console.log("Failed to Create Card");
        console.log(error);
      }
    })
  }
}
