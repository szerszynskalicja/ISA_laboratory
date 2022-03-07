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
    private String name;
    private String subject;
    private String className;

    public static Function<CreateTutorRequest, Tutor> dtoToEntityMapper() {
        return request -> Tutor.builder()
                .id(request.getId())
                .name(request.getName())
                .subject(request.getSubject())
                .className(request.getClassName())
                .build();
    }
}
