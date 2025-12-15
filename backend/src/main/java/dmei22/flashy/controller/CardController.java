package dmei22.flashy.controller;

import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.dto.review.ReviewCreateRequest;
import dmei22.flashy.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card/{cardId}")
@CrossOrigin("http://localhost:4200/")
public class CardController {

    private final CardService cardService;

    public CardController(
            CardService cardService
    ) {
        this.cardService = cardService;
    }

    // CREATE
    @PostMapping("/review")
    public ResponseEntity<?> createReview(
            @PathVariable("cardId") Long cardId,
            @RequestBody ReviewCreateRequest request
    ) {
        this.cardService.createReview(cardId, request);

        return ResponseEntity.noContent().build();
    }

    // READ
    @GetMapping
    public ResponseEntity<?> getCard(@PathVariable("cardId") Long cardId) {
        CardDetailsDto response = this.cardService.getCard(cardId);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping
    public ResponseEntity<?> updateCard(
            @PathVariable("cardId") Long cardId,
            @RequestBody CardUpdateRequest request
    ) {
        CardDetailsDto response = this.cardService.updateCard(cardId, request);

        return ResponseEntity.ok(response);
    }

    // DELETE

}
