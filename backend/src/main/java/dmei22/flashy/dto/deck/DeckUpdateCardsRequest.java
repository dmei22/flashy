package dmei22.flashy.dto.deck;

import lombok.Data;

import java.util.Set;

@Data
public class DeckUpdateCardsRequest {
    public Long deckId;
    public Set<Long> cardIds;
}
