package dmei22.flashy.service;

import dmei22.flashy.dto.card.CardCreateRequest;
import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.deck.*;
import dmei22.flashy.model.Card;
import dmei22.flashy.model.Deck;
import dmei22.flashy.model.Image;
import dmei22.flashy.repository.CardRepository;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.service.mapper.CardMapper;
import dmei22.flashy.service.mapper.DeckMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class DeckService {

    private final DeckRepository deckRepository;
    private final CardRepository cardRepository;
    private final ImageService imageService;

    public DeckService(
            DeckRepository deckRepository, CardRepository cardRepository,
            ImageService imageService
    ) {
        this.deckRepository = deckRepository;
        this.cardRepository = cardRepository;
        this.imageService = imageService;
    }

    // CREATE
    public DeckOverviewDto createDeck(DeckCreateRequest request) {
        Deck deck = new Deck();

        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckOverviewDto(this.deckRepository.save(deck));
    }

    public Card createCard(
            Long deckId,
            CardCreateRequest request
    ) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Card card = new Card();

        card.setFront(request.getFront());
        card.setBack(request.getBack());
        card.setDeck(deck);
        card.setLastReviewDate(LocalDate.now());
        card.setDueDate(LocalDate.now());
        deck.getCards().add(card);

        return card;
    }

    public Image uploadImage(
            Long deckId,
            MultipartFile file
    ) {
        Image image = this.imageService.upload(file);
        Deck deck = this.deckRepository.findById(deckId).get();

        deck.setImage(image);

        this.deckRepository.save(deck);
        return image;
    }

    // READ
    public DeckDetailsDto getDeck(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();

        return DeckMapper.toDeckDetailsDto(deck);
    }

    public List<DeckOverviewDto> all() {
        List<DeckOverviewDto> decks = this.deckRepository.findAll()
                .stream()
                .map(DeckMapper::toDeckOverviewDto)
                .toList();

        return decks;
    }

    public Image getImage(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Image image = deck.getImage();

        return image;
    }

    public List<CardDetailsDto> getCardsDue(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();
        LocalDate today = LocalDate.now();

        List<CardDetailsDto> cards = deck.getCards().stream()
                .filter(card -> today.isAfter(card.getDueDate()))
                .map(CardMapper::toCardDetailsDto)
                .toList();

        return cards;
    }

    // UPDATE
    public DeckDetailsDto updateDeck(Long deckId, DeckUpdateRequest request) {
        Deck deck = this.deckRepository.findById(deckId).get();

        deck.setName(request.getName());
        deck.setDescription(request.getDescription());

        return DeckMapper.toDeckDetailsDto(this.deckRepository.save(deck));
    }

    public void updateImage(
            Long deckId,
            MultipartFile file
    ) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Image image = this.imageService.update(deck.getImage().getId(), file);

        deck.setImage(image);

        this.deckRepository.save(deck);
    }

    // DELETE
    public void deleteDeck(Long id) {
        this.deckRepository.deleteById(id);
    }

    public void deleteImage(Long deckId) {
        Deck deck = this.deckRepository.findById(deckId).get();

        deck.setImage(null);

        this.deckRepository.save(deck);
    }

    public void deleteCard(Long deckId, Long cardIid) {
        Deck deck = this.deckRepository.findById(deckId).get();
        Card card = this.cardRepository.findById(cardIid).get();
        deck.getCards().remove(card);
        this.cardRepository.deleteById(cardIid);
    }
}
