package dmei22.flashy.service.mapper;


import dmei22.flashy.dto.card.CardDetailsDto;
import dmei22.flashy.dto.card.CardOverviewDto;
import dmei22.flashy.model.Card;

import java.time.LocalDate;

public class CardMapper {

    public static CardOverviewDto toCardOverviewDto(Card card) {
        CardOverviewDto dto = new CardOverviewDto();
        dto.setId(card.getId());
        dto.setFront(card.getFront());
        dto.setBack(card.getBack());
        dto.setDue(LocalDate.now().isAfter(card.getDueDate()));

        return dto;
    }

    public static CardDetailsDto toCardDetailsDto(Card card) {
        CardDetailsDto dto = new CardDetailsDto();
        dto.setId(card.getId());
        dto.setFront(card.getFront());
        dto.setBack(card.getBack());

        return dto;
    }
}
