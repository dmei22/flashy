package dmei22.flashy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
public class Image {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private String fileType;

    @Lob
    private byte[] bytes;

    private Instant updateAt;

    @PrePersist
    public void prePersist() {
        this.updateAt = Instant.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.updateAt = Instant.now();
    }
}
