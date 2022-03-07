package laboratoria.aui.rest.event;

import laboratoria.aui.rest.entity.Tutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class TutorEventRepository {
    private RestTemplate restTemplate;

    @Autowired
    public TutorEventRepository(@Value("${laboratoria.aui.url}") String baseUrl){
        restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }

    public void delete(Tutor tutor){
        restTemplate.delete("/tutors/{ID}",tutor.getId());
    }

    public void create(Tutor tutor){
        restTemplate.postForLocation("/tutors", CreateTutorRequest.entityToDtoMapper().apply(tutor));
    }
}
