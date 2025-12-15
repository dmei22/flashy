package dmei22.flashy.controller;

import dmei22.flashy.dto.card.CardCreateRequest;
import dmei22.flashy.dto.deck.*;
import dmei22.flashy.model.Card;
import dmei22.flashy.model.Image;
import dmei22.flashy.service.DeckService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/deck")
@CrossOrigin("http://localhost:4200/")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createDeck(
            @RequestBody DeckCreateRequest request
    ) {
        DeckOverviewDto response = this.deckService.createDeck(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deckId}/card")
    public ResponseEntity<?> createCard(
            @PathVariable("deckId") Long deckId,
            @RequestBody CardCreateRequest request
    ) {
        Card response = this.deckService.createCard(deckId, request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deckId}/image")
    public ResponseEntity<Long> uploadImage(
            @PathVariable("deckId") Long deckId,
            @RequestParam MultipartFile file
    ) {
        Image image = this.deckService.uploadImage(deckId, file);

        return ResponseEntity.ok(image.getId());
    }

    // READ
    @GetMapping("/{deckId}")
    public ResponseEntity<?> getDeck(@PathVariable("deckId") Long deckId) {
        DeckDetailsDto response = this.deckService.getDeck(deckId);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getDecks() {
        List<DeckOverviewDto> decks = this.deckService.all();

        return ResponseEntity.ok(decks);
    }

    @GetMapping("/{deckId}/image")
    public ResponseEntity<?> getImage(@PathVariable("deckId") Long deckId) {
        Image image = this.deckService.getImage(deckId);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .body(image.getBytes());
    }

    // UPDATE
    @PutMapping("/{deckId}")
    public ResponseEntity<?> updateDeck(
            @PathVariable("deckId") Long deckId,
            @RequestBody DeckUpdateRequest request
    ) {
        DeckDetailsDto response = this.deckService.updateDeck(deckId, request);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{deckId}/image")
    public ResponseEntity<?> updateImage(
            @PathVariable("deckId") Long deckId,
            @RequestParam MultipartFile file
    ) {
        this.deckService.updateImage(deckId, file);

        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{deckId}")
    public ResponseEntity<?> deleteDeck(@PathVariable("deckId") Long id) {
        this.deckService.deleteDeck(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{deckId}/card/{cardId}")
    public ResponseEntity<?> deleteCard(
            @PathVariable("deckId") Long deckId,
            @PathVariable("cardId") Long cardId
    ) {
        this.deckService.deleteCard(deckId, cardId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{deckId}/image")
    public ResponseEntity<?> deleteImage(@PathVariable("deckId") Long deckId) {
        this.deckService.deleteImage(deckId);

        return ResponseEntity.noContent().build();
    }
}
