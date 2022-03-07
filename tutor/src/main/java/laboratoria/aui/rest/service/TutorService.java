package laboratoria.aui.rest.service;

import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.event.TutorEventRepository;
import laboratoria.aui.rest.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TutorService {
    private TutorRepository repository;
    private TutorEventRepository eventRepository;

    @Autowired
    public TutorService(TutorRepository tutorRepository, TutorEventRepository eventRepository){
        this.repository = tutorRepository;
        this.eventRepository = eventRepository;
    }

    @Transactional
    public void update(Tutor tutor){this.repository.save(tutor);}

    @Transactional
    public void create(Tutor tutor){
        this.repository.save(tutor);
        this.eventRepository.create(tutor);}

    @Transactional
    public void delete(Tutor tutor){
        this.repository.delete(tutor);
        this.eventRepository.delete(tutor);}

    public List<Tutor> findAll(){return this.repository.findAll();}
    public Optional<Tutor> find(UUID idTutor){return this.repository.findById(idTutor);}
}
