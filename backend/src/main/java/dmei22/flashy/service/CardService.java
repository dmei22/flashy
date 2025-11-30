package dmei22.flashy.service;

import dmei22.flashy.dto.card.CardCreateRequest;
import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.model.Card;
import dmei22.flashy.model.Deck;
import dmei22.flashy.repository.CardRepository;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.service.mapper.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final DeckRepository deckRepository;

    public CardService(CardRepository cardRepository, DeckRepository deckRepository) {
        this.cardRepository = cardRepository;
        this.deckRepository = deckRepository;
    }

    // CREATE
    public Card create(CardCreateRequest request) {
        Deck deck = this.deckRepository.findById(request.getDeckId()).get();
        Card card = new Card();

        // Update entities bidirectionally
        card.setFront(request.getFront());
        card.setBack(request.getBack());
        card.setDeck(deck);
        deck.getCards().add(card);

        // Save entity with the owning side is enough for DB update on both sides
        return this.cardRepository.save(card);
    }

    // READ
    public CardDetailsDto findById(Long id) {
        Card card = this.cardRepository.findById(id).get();
        return CardMapper.toCardDetailsDto(card);
    }

    public List<Card> all() {
        return this.cardRepository.findAll();
    }

    // UPDATE
    public CardDetailsDto update(CardUpdateRequest request) {
        Card card = this.cardRepository.findById(request.getCardId()).get();
        card.setFront(request.getFront());
        card.setBack(request.getBack());

        return CardMapper.toCardDetailsDto(this.cardRepository.save(card));
    }

    // DELETE
    public void deleteById(Long id) {
        this.cardRepository.deleteById(id);
    }
}
