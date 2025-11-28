package dmei22.flashy.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
public class Deck {

    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    private String name;
    private String description;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL)

    private Set<Card> cards = new HashSet<>();
}
