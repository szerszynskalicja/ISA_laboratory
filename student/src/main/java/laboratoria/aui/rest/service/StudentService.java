package laboratoria.aui.rest.service;

import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.repository.StudentRepository;
import laboratoria.aui.rest.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StudentService {
    private StudentRepository repository;
    private TutorRepository tutorRepository;

    @Autowired
    public StudentService(StudentRepository repository, TutorRepository tutorRepository){this.repository = repository;
    this.tutorRepository = tutorRepository;}

    @Transactional
    public void update(Student student){this.repository.save(student);}
    @Transactional
    public Student save(Student student){return this.repository.save(student);}
    @Transactional
    public void delete(Student student){this.repository.delete(student);}
    public List<Student> findAll(){return this.repository.findAll();}
    public Optional<Student> find(UUID idStudent){return this.repository.findById(idStudent);}
    public List<Student> findAll(Tutor tutor){return this.repository.findAllByTutor(tutor);}
    public Optional<Student> find(Tutor tutor, UUID id){
        Optional<Tutor> tutor1 = tutorRepository.findById(tutor.getId());
        if(tutor1.isPresent()){
            return this.repository.findByIdAndTutor(id, tutor1.get());
        }
        else{
            return Optional.empty();
        }
    }
}
