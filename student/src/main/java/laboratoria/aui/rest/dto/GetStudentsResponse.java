package laboratoria.aui.rest.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetStudentsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Student {

        private UUID id;

        private String name;

    }
    @Singular
    private List<Student> students;

    public static Function<Collection<laboratoria.aui.rest.entity.Student>,GetStudentsResponse> entityToDtoMapper(){
        return students -> {
            GetStudentsResponseBuilder responseBuilder = GetStudentsResponse.builder();
            students.stream()
                    .map(student -> Student.builder()
                            .id(student.getId())
                            .name(student.getName()).build()).forEach(responseBuilder::student);
            return responseBuilder.build();
        };
    }

}
