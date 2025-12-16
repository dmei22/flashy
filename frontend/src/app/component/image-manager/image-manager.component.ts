import {Component, effect, inject, input, OnInit, output} from '@angular/core';
import {ReactiveFormsModule} from "@angular/forms";
import {DeckDetails} from "../../model/Deck";
import {ImageService} from "../../service/image.service";

@Component({
  selector: 'app-image-manager',
  imports: [
    ReactiveFormsModule
  ],
  templateUrl: './image-manager.component.html',
  styleUrl: './image-manager.component.css'
})
export class ImageManagerComponent implements OnInit {

  private imageService = inject(ImageService);
  public entity = input.required<DeckDetails>();
  public updateOutput = output<void>();

  protected selectedFile!: File;

  constructor() {
    effect(() => {
      this.entity();
      this.setMapping();
    });
  }

  public ngOnInit() {
  }

  protected onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    const file = input.files?.[0];
    if (!file) return;

    this.selectedFile = file;
  }

  protected save(): void {
    this.closeImageModal();

    const formData = new FormData();
    formData.append("file", this.selectedFile);

    if (this.entity().imageVersion) {
      console.log("updating");
      this.update(formData);
    } else {
      console.log("uploading");
      this.upload(formData);
    }
  }

  protected deleteCurrent(): void {
    this.closeImageModal();

    this.imageService.delete().subscribe({
      next: (response: void) => {
        this.updateOutput.emit();
      },
      error: (error) => {
        console.log(error);
      }
    })
  }

  // Helper methods
  private setMapping(): void {
    if (this.entity() instanceof DeckDetails) {
      this.imageService.setMapping("deck", this.entity().id);
    }

    // Can add other service options later
  }

  private closeImageModal(): void {
    document.getElementById("image-upload-modal-close")?.click();
  }

  private upload(formData: FormData): void {
    this.imageService.upload(formData).subscribe({
      next: (response: number) => {
        this.updateOutput.emit();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }

  private update(formData: FormData): void {
    this.imageService.update(formData).subscribe({
      next: (response: number) => {
        this.updateOutput.emit();
      },
      error: (error) => {
        console.log(error);
      }
    });
  }
}
