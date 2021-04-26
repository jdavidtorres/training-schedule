package co.com.jdti.app.controllers;

import co.com.jdti.app.dtos.Event;
import co.com.jdti.app.dtos.Instructor;
import co.com.jdti.app.models.entities.EventEntity;
import co.com.jdti.app.models.entities.InstructorEntity;
import co.com.jdti.app.services.IInstructorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class TrainerController {

    @Autowired
    private IInstructorServices iInstructorServices;

    @GetMapping
    public ResponseEntity<List<InstructorEntity>> findAll() {
        List<InstructorEntity> instructorEntityList = iInstructorServices.findAllInstructors();
        if (instructorEntityList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(instructorEntityList);
    }

    @PostMapping
    public ResponseEntity<InstructorEntity> save(@Valid @RequestBody Instructor instructor, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        var instructorSave = new InstructorEntity(instructor.getName(), instructor.getLastname(), instructor.getBirthday());
        return ResponseEntity.status(HttpStatus.CREATED).body(iInstructorServices.save(instructorSave));
    }

    @PostMapping("/assign-event")
    public ResponseEntity<InstructorEntity> assignEvent(@RequestParam String idInstructor, @Valid @RequestBody Event event, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        Optional<InstructorEntity> instructor = iInstructorServices.findInstructorById(idInstructor);
        if (instructor.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        var newEvent = new EventEntity(event.getName(), event.getStartDate(), event.getEndDate(), event.getDescription());

        var instructorEvent = new InstructorEntity(instructor.get().getId(), instructor.get().getName(), instructor.get().getLastname(), instructor.get().getBirthday());
        instructorEvent.getEvents().add(newEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(iInstructorServices.assignEvent(instructorEvent));
    }
}
