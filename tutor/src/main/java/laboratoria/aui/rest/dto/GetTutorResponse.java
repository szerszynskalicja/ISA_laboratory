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
public class GetTutorResponse {
    private UUID id;
    private String name;
    private String subject;
    private String className;

    public static Function<Tutor, GetTutorResponse> entityToDtoMapper() {
        return tutor -> GetTutorResponse.builder()
                .name(tutor.getName())
                .id(tutor.getId())
                .subject(tutor.getSubject())
                .className(tutor.getClassName())
                .build();
    }

}
