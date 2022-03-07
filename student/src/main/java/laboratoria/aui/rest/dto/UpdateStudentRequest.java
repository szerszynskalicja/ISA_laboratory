package laboratoria.aui.rest.dto;

import laboratoria.aui.rest.entity.Student;
import lombok.*;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateStudentRequest {
    private String name;
    private int age;

    public static BiFunction<Student, UpdateStudentRequest, Student> dtoToEntityUpdater(){
        return (student, request) -> {
            student.setName(request.getName());
            student.setAge(request.getAge());
            return student;
        };
    }
}
