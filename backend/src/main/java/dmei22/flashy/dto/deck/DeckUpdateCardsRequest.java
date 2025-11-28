package dmei22.flashy.dto.deck;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DeckUpdateCardsRequest {
    public Long deckId;
    public Set<Long> cardIds;
}
