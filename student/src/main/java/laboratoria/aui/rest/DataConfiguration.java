package laboratoria.aui.rest;
import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.service.StudentService;
import laboratoria.aui.rest.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;

@Component
public class DataConfiguration {
    private final StudentService studentService;
    private final TutorService tutorService;

    @Autowired
    public DataConfiguration(StudentService studentService, TutorService tutorService) {
        this.studentService = studentService;
        this.tutorService = tutorService;
    }


    @PostConstruct
    private synchronized void init() {



        Student student1 = new Student("Barbara Bok", 12, null);
        Student student2 = new Student("Zuzanna Zos", 12, null);
        Student student3 = new Student("Tomasz Zuch", 10, null);
        Student student4 = new Student("Weronika Wiktoria", 13, null);


        Tutor tutor1 = new Tutor(UUID.fromString("0d9ea537-61df-4412-8ef6-9583426af355"),  List.of(student1, student2));
        Tutor tutor2 = new Tutor(UUID.fromString("a97399cc-81ef-4961-9113-ee0c0fe7748c"), List.of(student3));
        Tutor tutor3 = new Tutor(UUID.fromString("7edbe2e3-d804-4cfc-b9a4-fc637e91a4c8"), List.of(student4) );

        tutorService.save(tutor1);
        tutorService.save(tutor2);
        tutorService.save(tutor3);
        student1.setTutor(tutor1);
        student2.setTutor(tutor1);
        student3.setTutor(tutor2);
        student4.setTutor(tutor3);
        studentService.save(student1);
        studentService.save(student2);
        studentService.save(student3);
        studentService.save(student4);

    }
}

