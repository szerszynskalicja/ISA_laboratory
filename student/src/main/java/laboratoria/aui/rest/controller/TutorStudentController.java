package laboratoria.aui.rest.controller;

import laboratoria.aui.rest.dto.CreateStudentRequest;
import laboratoria.aui.rest.dto.GetStudentResponse;
import laboratoria.aui.rest.dto.GetStudentsResponse;
import laboratoria.aui.rest.dto.UpdateStudentRequest;
import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.service.StudentService;
import laboratoria.aui.rest.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/tutors/{idTutor}/students")
public class TutorStudentController {

    private TutorService tutorService;
    private StudentService studentService;
    @Autowired
    public TutorStudentController(TutorService tutorService, StudentService studentService) {
        this.tutorService = tutorService;
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<GetStudentsResponse> getStudents(@PathVariable("idTutor") UUID idTutor) {
        Optional<Tutor> tutor = tutorService.find(idTutor);
        return tutor.map(value -> ResponseEntity.ok(GetStudentsResponse.entityToDtoMapper().apply(studentService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{idStudent}")
    public ResponseEntity<GetStudentResponse> getStudent(@PathVariable("idTutor") UUID idTutor,
                                                                    @PathVariable("idStudent") UUID idStudent) {
        Optional<Tutor> tutor = tutorService.find(idTutor);
        return studentService.find(tutor.get(), idStudent)
                    .map(value -> ResponseEntity.ok(GetStudentResponse.entityToDtoMapper().apply(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Void> createStudent(@PathVariable("idTutor") UUID idTutor,
                                              @RequestBody CreateStudentRequest request,
                                              UriComponentsBuilder builder) {
        Optional<Tutor> tutor = tutorService.find(idTutor);
        if(tutor.isPresent()){
            Student student = CreateStudentRequest
                 /*   .dtoToEntityMapper(id -> tutorService.find(id).orElseThrow())*/
                    .dtoToEntityMapper(tutor :: get)
                    .apply(request);
            student = studentService.save(student);
            return ResponseEntity.created(builder.pathSegment("api", "tutors", "{idTutor}", "students", "{idStudent}")
                    .buildAndExpand(tutor.get().getId(), student.getId()).toUri()).build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{idStudent}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("idTutor") UUID idTutor,
                                                @PathVariable("idStudent") UUID idStudent) {
        Optional<Tutor> tutor = tutorService.find(idTutor);
        if(!tutor.isPresent()){
            return ResponseEntity.notFound().build();
        }

        Optional<Student> student = studentService.find(tutor.get(), idStudent);
        if(student.isPresent()){
            studentService.delete(student.get());
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("{idStudent}")
    public ResponseEntity<Void> updateStudent(@PathVariable("idTutor") UUID idTutor,
                                                @RequestBody UpdateStudentRequest request,
                                                @PathVariable("idStudent") UUID idStudent) {
        Optional<Tutor> tutor = tutorService.find(idTutor);
        if(!tutor.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Optional<Student> student = studentService.find(tutor.get(), idStudent);
        if(student.isPresent()){
            UpdateStudentRequest.dtoToEntityUpdater().apply(student.get(), request);
            studentService.update(student.get());
            return ResponseEntity.accepted().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
    }

