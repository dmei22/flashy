package dmei22.flashy.controller;

import dmei22.flashy.dto.deck.DeckDetailsDto;
import dmei22.flashy.dto.deck.DeckOverviewDto;
import dmei22.flashy.dto.deck.DeckUpdateCardsRequest;
import dmei22.flashy.model.Deck;
import dmei22.flashy.service.DeckService;
import dmei22.flashy.service.mapper.DeckMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/deck")
@CrossOrigin("http://localhost:4200/")
public class DeckController {

    private final DeckService deckService;

    public DeckController(DeckService deckService) {
        this.deckService = deckService;
    }

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Deck deck) {
        Deck response = this.deckService.create(deck);
        return ResponseEntity.ok(DeckMapper.toDeckDetailsDto(response));
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
    public ResponseEntity<?> update(@RequestBody Deck deck) {
        Deck response = this.deckService.update(deck);
        return ResponseEntity.ok(DeckMapper.toDeckDetailsDto(response));
    }

    @PutMapping("/add/cards")
    public ResponseEntity<?> addCards(@RequestBody DeckUpdateCardsRequest request) {
        Deck response = this.deckService.addCards(request);
        return ResponseEntity.ok(DeckMapper.toDeckDetailsDto(response));
    }

    @PutMapping("/remove/cards")
    public ResponseEntity<?> removeCards(@RequestBody DeckUpdateCardsRequest request) {
        Deck response = this.deckService.removeCards(request);
        return ResponseEntity.ok(DeckMapper.toDeckDetailsDto(response));
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
        this.deckService.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
