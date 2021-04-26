package co.com.jdti.app.controllers;

import co.com.jdti.app.models.entities.InstructorEntity;
import co.com.jdti.app.services.IInstructorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
