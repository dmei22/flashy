package dmei22.flashy.dto.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardUpdateRequest {
    private Long cardId;
    private String front;
    private String back;
}
