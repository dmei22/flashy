package dmei22.flashy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Card {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String front;
    private String back;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "deck_id")
    @JsonIgnore
    private Deck deck;

    @Enumerated(EnumType.STRING)
    private Interval interval = Interval.NEW;

    private LocalDate lastReviewDate;
    private LocalDate nextReviewDate;
}
