package dmei22.flashy.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCreateRequest {
    private Long deckId;
    private String front;
    private String back;
}
