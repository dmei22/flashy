package dmei22.flashy.service.mapper;


import dmei22.flashy.dto.card.CardOverviewDto;
import dmei22.flashy.model.Card;

public class CardMapper {

    public static CardOverviewDto toCardOverviewDto(Card card) {
        CardOverviewDto dto = new CardOverviewDto();
        dto.setId(card.getId());
        dto.setFront(card.getFront());
        dto.setBack(card.getBack());

        return dto;
    }
}
