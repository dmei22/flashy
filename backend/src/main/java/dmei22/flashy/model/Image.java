package dmei22.flashy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
}
