package dmei22.flashy.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;

    private LocalDate reviewDate;
    private boolean succes;

    public Review() {
        this.reviewDate = LocalDate.now();
    }
}
