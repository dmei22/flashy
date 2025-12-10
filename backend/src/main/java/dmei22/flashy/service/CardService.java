package dmei22.flashy.service;

import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardUpdateRequest;
import dmei22.flashy.model.Card;
import dmei22.flashy.repository.CardRepository;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.service.mapper.CardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CardService {

    private final CardRepository cardRepository;

    public CardService(
            CardRepository cardRepository
    ) {
        this.cardRepository = cardRepository;
    }

    // CREATE


    // READ
    public CardDetailsDto getCard(Long id) {
        Card card = this.cardRepository.findById(id).get();
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
