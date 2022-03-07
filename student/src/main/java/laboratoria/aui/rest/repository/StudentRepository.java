package laboratoria.aui.rest.repository;

import laboratoria.aui.rest.entity.Student;
import laboratoria.aui.rest.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {

    List<Student> findAllByTutor(Tutor tutor);

    Optional<Student> findByIdAndTutor(UUID id, Tutor tutor);
}
