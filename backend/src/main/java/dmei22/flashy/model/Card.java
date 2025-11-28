package dmei22.flashy.model;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    private Deck deck;
}
