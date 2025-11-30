import {Component, input} from '@angular/core';

@Component({
  selector: 'app-card-face',
  imports: [],
  templateUrl: './card-face.component.html',
  styleUrl: './card-face.component.css'
})
export class CardFaceComponent {

  public label = input<string>();
  public body = input<string>();
}
