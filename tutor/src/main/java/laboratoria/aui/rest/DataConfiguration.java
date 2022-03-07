package laboratoria.aui.rest;

import laboratoria.aui.rest.entity.Tutor;
import laboratoria.aui.rest.service.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Component
public class DataConfiguration {
    private final TutorService tutorService;

    @Autowired
    public DataConfiguration(TutorService tutorService){
        this.tutorService = tutorService;
    }

    @PostConstruct
    private synchronized void init() {
        Tutor tutor1 = Tutor.builder()
                .id(UUID.fromString("0d9ea537-61df-4412-8ef6-9583426af355"))
                .name("Anna Kowalska")
                .subject("Przyroda")
                .className("5A")
                .build();
        Tutor tutor2 = Tutor.builder()
                .id(UUID.fromString("a97399cc-81ef-4961-9113-ee0c0fe7748c"))
                .name("Bartosz Kowalski")
                .subject("Historia")
                .className("4C")
                .build();
        Tutor tutor3 = Tutor.builder()
                .id(UUID.fromString("7edbe2e3-d804-4cfc-b9a4-fc637e91a4c8"))
                .name("Krzysztof Zalewski")
                .subject("Angielski")
                .className("8B")
                .build();
        tutorService.create(tutor1);
        tutorService.create(tutor2);
        tutorService.create(tutor3);
    }
}
