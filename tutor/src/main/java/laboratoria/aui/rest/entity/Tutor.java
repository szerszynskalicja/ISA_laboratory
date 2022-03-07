package laboratoria.aui.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Builder
@Table(name = "tutor")
public class Tutor {
    @Id
    private UUID id;
    public String name;
    public String subject;
    public String className;
}
