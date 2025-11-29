package dmei22.flashy.service;

import dmei22.flashy.dto.deck.*;
import dmei22.flashy.model.Card;
import dmei22.flashy.model.Deck;
import dmei22.flashy.repository.CardRepository;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.service.mapper.DeckMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeckService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;

    public DeckService(DeckRepository deckRepository,
                       CardRepository cardRepository) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
    }

    // CREATE
    public DeckOverviewDto create(DeckCreateRequest request) {
        Deck deck = new Deck();
        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckOverviewDto(this.deckRepository.save(deck));
    }

    // READ
    public DeckDetailsDto findById(Long id) {
        Deck deck = this.deckRepository.findById(id).get();

        return DeckMapper.toDeckDetailsDto(deck);
    }

    public List<DeckOverviewDto> all() {
        List<DeckOverviewDto> decks = this.deckRepository.findAll()
                .stream()
                .map(DeckMapper::toDeckOverviewDto)
                .toList();

        return decks;
    }

    // UPDATE
    public DeckDetailsDto update(DeckUpdateRequest request) {
        Deck deck = this.deckRepository.findById(request.getId()).get();
        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckDetailsDto(this.deckRepository.save(deck));
    }

    public DeckDetailsDto addCards(DeckUpdateCardsRequest request) {
        List<Card> cards = this.cardRepository.findAllById(request.cardIds);
        Deck deck = this.deckRepository.findById(request.deckId).get();

        for (Card card : cards) {
            deck.getCards().add(card);
            card.setDeck(deck);
        }

        return DeckMapper.toDeckDetailsDto(this.deckRepository.save(deck));
    }

    public DeckDetailsDto removeCards(DeckUpdateCardsRequest request) {
        List<Card> cards = this.cardRepository.findAllById(request.cardIds);
        Deck deck = this.deckRepository.findById(request.deckId).get();

        for (Card card : cards) {
            deck.getCards().remove(card);
            card.setDeck(null);
        }

        return DeckMapper.toDeckDetailsDto(this.deckRepository.save(deck));
    }

    // DELETE
    public void deleteById(Long id) {
        this.deckRepository.deleteById(id);
    }
}
