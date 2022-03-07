package laboratoria.aui.rest.controller;

import laboratoria.aui.rest.dto.CreateTutorRequest;
import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/tutors")
public class TutorController {
    private TutorService tutorService;

    @Autowired
    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }


    @PostMapping
    public ResponseEntity<Void> createTutor(@RequestBody CreateTutorRequest request, UriComponentsBuilder builder) {
        Tutor tutor = CreateTutorRequest
                .dtoToEntityMapper()
                .apply(request);
        tutor = tutorService.save(tutor);
        return ResponseEntity.created(builder.pathSegment("api", "tutors", "{id}")
                .buildAndExpand(tutor.getId()).toUri()).build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTutor(@PathVariable("id") UUID id) {
        Optional<Tutor> tutor = tutorService.find(id);
        if (tutor.isPresent()) {
            tutorService.delete(tutor.get());//tutaj nie wiem czy jedank nie po id
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}