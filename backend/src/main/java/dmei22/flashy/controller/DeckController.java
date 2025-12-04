package dmei22.flashy.controller;

import dmei22.flashy.dto.deck.*;
import dmei22.flashy.model.Deck;
import dmei22.flashy.model.Image;
import dmei22.flashy.service.DeckService;
import dmei22.flashy.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/deck")
@CrossOrigin("http://localhost:4200/")
public class DeckController {

    private final DeckService deckService;
    private final ImageService imageService;

    public DeckController(
            DeckService deckService,
            ImageService imageService
    ) {
        this.deckService = deckService;
        this.imageService = imageService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DeckCreateRequest request) {
        DeckOverviewDto response = this.deckService.create(request);

        return ResponseEntity.ok(response);
    }

    // TODO: CREATE IMAGE
    @PostMapping("/{id}/image")
    public ResponseEntity<Long> uploadImage(
            @PathVariable("id") Long id,
            @RequestParam MultipartFile file
    ) {
        Image image = this.deckService.uploadImage(id, file);

        return ResponseEntity.ok(image.getId());
    }

    // READ
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        DeckDetailsDto response = this.deckService.findById(id);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all() {
        List<DeckOverviewDto> decks = this.deckService.all();

        return ResponseEntity.ok(decks);
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody DeckUpdateRequest request) {
        DeckDetailsDto response = this.deckService.update(request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable("id") Long deckId,
            @RequestParam MultipartFile file
    ) {
        this.deckService.updateImage(deckId, file);

        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.deckService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}/image")
    public ResponseEntity<?> deleteImage(@PathVariable("id") Long deckId) {
        this.deckService.deleteImage(deckId);

        return ResponseEntity.noContent().build();}
}
