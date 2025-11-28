package dmei22.flashy.dto.deck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckUpdateRequest {
    private Long id;
    private String name;
    private String description;
}
