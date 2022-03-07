package laboratoria.aui.rest.event;

import laboratoria.aui.rest.entity.Tutor;
import lombok.*;

import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateTutorRequest {
    private UUID id;

    public static Function<Tutor, CreateTutorRequest> entityToDtoMapper(){
        return entity -> CreateTutorRequest.builder().id(entity.getId()).build();
    }
}
