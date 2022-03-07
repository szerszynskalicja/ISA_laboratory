package laboratoria.aui.rest.dto;

import laboratoria.aui.rest.entity.Student;
import lombok.*;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudentResponse {
    private String name;
    private int age;
    public static Function<Student, GetStudentResponse> entityToDtoMapper() {
        return Student -> GetStudentResponse.builder()
                .name(Student.getName())
                .age(Student.getAge())
                .build();
    }
}
