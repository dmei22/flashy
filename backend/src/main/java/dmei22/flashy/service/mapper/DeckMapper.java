package dmei22.flashy.service.mapper;

import dmei22.flashy.dto.card.CardOverviewDto;
import dmei22.flashy.dto.deck.DeckDetailsDto;
import dmei22.flashy.dto.deck.DeckOverviewDto;
import dmei22.flashy.model.Deck;

import java.util.List;

public class DeckMapper {

    public static DeckOverviewDto toDeckOverviewDto(Deck deck) {
        DeckOverviewDto dto = new DeckOverviewDto();
        dto.setId(deck.getId());
        dto.setName(deck.getName());
        dto.setDescription(deck.getDescription());

        return dto;
    }

    public static DeckDetailsDto toDeckDetailsDto(Deck deck) {
        DeckDetailsDto dto = new DeckDetailsDto();
        dto.setId(deck.getId());
        dto.setName(deck.getName());
        dto.setDescription(deck.getDescription());
        List<CardOverviewDto> cards = deck
                .getCards()
                .stream()
                .map(CardMapper::toCardOverviewDto)
                .toList();
        dto.setCards(cards);

        return dto;
    }
}
