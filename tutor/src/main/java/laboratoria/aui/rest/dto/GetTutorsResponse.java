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
public class GetTutorsResponse {
    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Tutor {

        private UUID id;

        private String name;

    }
    @Singular
    private List<Tutor> tutors;

    public static Function<Collection<laboratoria.aui.rest.entity.Tutor>,GetTutorsResponse> entityToDtoMapper(){
        return tutors -> {
            GetTutorsResponseBuilder responseBuilder = GetTutorsResponse.builder();
            tutors.stream()
                    .map(tutor -> Tutor.builder()
                            .id(tutor.getId())
                            .name(tutor.getName()).build()).forEach(responseBuilder::tutor);
            return responseBuilder.build();
        };
    }
}
