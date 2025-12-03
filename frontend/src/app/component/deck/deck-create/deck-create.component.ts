import {Component, inject, OnInit} from '@angular/core';
import {DeckService} from "../../../service/deck.service";
import {FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {DeckDetails, DeckOverview} from "../../../model/Deck";
import {Router} from "@angular/router";

@Component({
  selector: 'app-deck-create',
  imports: [
    ReactiveFormsModule,
  ],
  templateUrl: './deck-create.component.html',
  styleUrl: './deck-create.component.css'
})
export class DeckCreateComponent implements OnInit {

  private router = inject(Router);
  private deckService = inject(DeckService);
  private formBuilder = inject(FormBuilder);

  protected deckForm!: FormGroup;

  ngOnInit() {
    this.buildForm();
  }

  protected onSubmit(): void {
    document.getElementById("deck-create-modal-close")?.click();

    this.deckService.create(this.deckForm.value).subscribe({
      next: (response: DeckOverview) => {
        this.router.navigate(["deck/", response.id]);
      },
      error: (error) => {
        console.log(error);
    }
    });
  }

  // Helpers methods
  private buildForm(): void {
    this.deckForm = this.formBuilder.group({
      name: "",
      description: "",
    });
  }
}
