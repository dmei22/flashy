package dmei22.flashy.dto.deck;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeckOverviewDto {
    private Long id;
    private String name;
    private String description;
    private Long imageVersion;
}
