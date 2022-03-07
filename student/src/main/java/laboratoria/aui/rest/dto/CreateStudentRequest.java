package laboratoria.aui.rest.dto;

import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.entity.Tutor;
import lombok.*;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateStudentRequest {
    private String name;
    private int age;
    private UUID id;
/*
    public static Function<CreateStudentRequest, Student> dtoToEntityMapper(Function<UUID, Tutor> tutorFunction) {
        return request -> Student.builder()
                .name(request.getName())
                .age(request.getAge())
                .tutor(tutorFunction.apply(request.getId())).build();
    }
*/
    public static Function<CreateStudentRequest, Student> dtoToEntityMapper(Supplier<Tutor> tutorSupplier) {
        return request -> Student.builder()
            .name(request.getName())
            .age(request.getAge())
            .tutor(tutorSupplier.get()).build();
}
}
