package dmei22.flashy.service;

import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.dto.review.ReviewCreateRequest;
import dmei22.flashy.model.Card;
import dmei22.flashy.model.Level;
import dmei22.flashy.model.Review;
import dmei22.flashy.repository.CardRepository;
import dmei22.flashy.repository.ReviewRepository;
import dmei22.flashy.service.mapper.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;
    private final ReviewRepository reviewRepository;

    public CardService(
            CardRepository cardRepository,
            ReviewRepository reviewRepository
    ) {
        this.cardRepository = cardRepository;
        this.reviewRepository = reviewRepository;
    }

    // CREATE
    public void createReview(Long cardId, ReviewCreateRequest request) {
        Card card = this.cardRepository.findById(cardId).get();
        Review review = new Review();

        review.setCard(card);
        review.setReviewDate(LocalDate.now());
        review.setSucces(request.isSuccess());
        card.getReviews().add(review);
        if (request.isSuccess()) {
            card.setLevel(card.getLevel().next());
            card.setDueDate(LocalDate.now().plusDays(card.getLevel().getDays()));
        } else {
            card.setLevel(Level.NEW);
            card.setDueDate(LocalDate.now());
        }

        this.reviewRepository.save(review);
    }

    // READ
    public CardDetailsDto getCard(Long cardId) {
        Card card = this.cardRepository.findById(cardId).get();
        return CardMapper.toCardDetailsDto(card);
    }

    // UPDATE
    public CardDetailsDto updateCard(
            Long cardId,
            CardUpdateRequest request
    ) {
        Card card = this.cardRepository.findById(cardId).get();
        card.setFront(request.getFront());
        card.setBack(request.getBack());

        return CardMapper.toCardDetailsDto(this.cardRepository.save(card));
    }

    // DELETE
}
