package laboratoria.aui.rest.dto;
import laboratoria.aui.rest.entity.Tutor;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTutorRequest {
    private String name;
    private String subject;
    private String className;

    public static BiFunction<Tutor, UpdateTutorRequest, Tutor> dtoToEntityUpdater(){
        return (tutor, request) -> {
            tutor.setName(request.getName());
            tutor.setSubject(request.getSubject());
            tutor.setClassName(request.getClassName());
            return tutor;
        };
    }
}
