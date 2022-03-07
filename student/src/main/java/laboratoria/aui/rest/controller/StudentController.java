package laboratoria.aui.rest.controller;

import laboratoria.aui.rest.dto.CreateStudentRequest;
import laboratoria.aui.rest.dto.GetStudentResponse;
import laboratoria.aui.rest.dto.GetStudentsResponse;
import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.service.StudentService;
import laboratoria.aui.rest.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/students")
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }
    @GetMapping
    public ResponseEntity<GetStudentsResponse> getCharacters() {
        return ResponseEntity.ok(GetStudentsResponse.entityToDtoMapper().apply(studentService.findAll()));
    }

    @GetMapping("{id}")
    public ResponseEntity<GetStudentResponse> getCharacter(@PathVariable("id") UUID id) {
        return  studentService.find(id)
                .map(value -> ResponseEntity.ok(GetStudentResponse.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable("id") UUID id) {
        Optional<Student> student = studentService.find(id);
        if (student.isPresent()) {
            studentService.delete(student.get());//tutaj nie wiem czy jedank nie po id
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
