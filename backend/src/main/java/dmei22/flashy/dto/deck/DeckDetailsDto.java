package dmei22.flashy.dto.deck;


import dmei22.flashy.dto.card.CardOverviewDto;
import lombok.Data;

import java.util.List;

@Data
public class DeckDetailsDto {
    private Long id;
    private String name;
    private String description;
    private List<CardOverviewDto> cards;
}
