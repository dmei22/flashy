package dmei22.flashy.controller;

import dmei22.flashy.dto.card.CardCreateRequest;
import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.model.Card;
import dmei22.flashy.service.CardService;
import dmei22.flashy.service.DeckService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deck/{deckId}/card")
@CrossOrigin("http://localhost:4200/")
public class CardController {

    private final CardService cardService;
    private final DeckService deckService;

    public CardController(
            CardService cardService,
            DeckService deckService
    ) {
        this.cardService = cardService;
        this.deckService = deckService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<?> createCard(
            @PathVariable("deckId") Long deckId,
            @RequestBody CardCreateRequest request
    ) {
        Card response = this.deckService.createCard(deckId, request);

        return ResponseEntity.ok(response);
    }

    // READ
    @GetMapping("/{cardId}")
    public ResponseEntity<?> getCard(@PathVariable("cardId") Long cardId) {
        CardDetailsDto response = this.cardService.getCard(cardId);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{cardId}")
    public ResponseEntity<?> updateCard(
            @PathVariable("cardId") Long cardId,
            @RequestBody CardUpdateRequest request // TODO: adjust request
    ) {
        CardDetailsDto response = this.cardService.updateCard(cardId, request);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/{cardId}")
    public ResponseEntity<?> deleteCard(
            @PathVariable("cardId") Long cardId
    ) {
        this.deckService.deleteCard(cardId);

        return ResponseEntity.noContent().build();
    }
}
