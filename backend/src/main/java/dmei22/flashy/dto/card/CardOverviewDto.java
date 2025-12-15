package dmei22.flashy.dto.card;

import lombok.Data;

@Data
public class CardOverviewDto {
    private Long id;
    private String front;
    private String back;
    private boolean due;
}
