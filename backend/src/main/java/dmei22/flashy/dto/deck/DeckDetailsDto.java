package dmei22.flashy.dto.deck;


import dmei22.flashy.dto.card.CardOverviewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DeckDetailsDto {
    private Long id;
    private String name;
    private String description;
    private List<CardOverviewDto> cards;
    private Long imageVersion;
}
