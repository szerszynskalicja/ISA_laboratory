package laboratoria.aui.rest.dto;
import laboratoria.aui.rest.entity.Tutor;
import lombok.*;

import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateTutorRequest {
    private UUID id;

    public static Function<CreateTutorRequest, Tutor> dtoToEntityMapper() {
        return request -> Tutor.builder()
                .id(request.getId())
                .build();
    }
}
