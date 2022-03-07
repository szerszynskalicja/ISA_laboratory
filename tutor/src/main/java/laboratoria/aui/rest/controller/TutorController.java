package laboratoria.aui.rest.controller;

import laboratoria.aui.rest.dto.*;
import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.service.*;
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

    @GetMapping
    public ResponseEntity<GetTutorsResponse> getTutors() {
        return ResponseEntity.ok(GetTutorsResponse.entityToDtoMapper().apply(tutorService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetTutorResponse> getTutor(@PathVariable("id") UUID id) {
        return tutorService.find(id)
                .map(value -> ResponseEntity.ok(GetTutorResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> createTutor(@RequestBody CreateTutorRequest request, UriComponentsBuilder builder) {
        Tutor tutor = CreateTutorRequest
                .dtoToEntityMapper()
                .apply(request);
        tutorService.create(tutor);
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

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTutor(@RequestBody UpdateTutorRequest request, @PathVariable("id") UUID id) {
        Optional<Tutor> tutor = tutorService.find(id);
        if (tutor.isPresent()) {
            UpdateTutorRequest.dtoToEntityUpdater().apply(tutor.get(), request);
            tutorService.update(tutor.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}