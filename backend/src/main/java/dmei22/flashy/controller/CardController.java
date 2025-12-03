package dmei22.flashy.controller;

import dmei22.flashy.dto.card.CardCreateRequest;
import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.model.Card;
import dmei22.flashy.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
@CrossOrigin("http://localhost:4200/")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CardCreateRequest request) {
        Card response = this.cardService.create(request);

        return ResponseEntity.ok(response);
    }

    // READ
    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Long id) {
        CardDetailsDto response = this.cardService.findById(id);

        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody CardUpdateRequest request) {
        CardDetailsDto response = this.cardService.update(request);

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.cardService.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
