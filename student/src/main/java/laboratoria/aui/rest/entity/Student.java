package laboratoria.aui.rest.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "students")
public class Student {
    @Id
    private final UUID id = UUID.randomUUID();
    public String name;
    public int age;
    @ManyToOne(fetch = FetchType.LAZY)
    public Tutor tutor;
}
