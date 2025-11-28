package dmei22.flashy.service;

import dmei22.flashy.model.Card;
import dmei22.flashy.model.Review;
import dmei22.flashy.repository.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CardService {
    private final CardRepository cardRepository;

    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    // CREATE
    public Card create(Card card) {
        return this.cardRepository.save(card);
    }

    // READ
    public Card findById(Long id) {
        return this.cardRepository.findById(id).get();
    }

    public List<Card> all() {
        return this.cardRepository.findAll();
    }

    // UPDATE
    public Card update(Card card) {
        return this.cardRepository.save(card);
    }

    // DELETE
    public void deleteById(Long id) {
        this.cardRepository.deleteById(id);
    }
}
