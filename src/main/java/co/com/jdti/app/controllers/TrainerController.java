package co.com.jdti.app.controllers;

import co.com.jdti.app.dtos.Trainer;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/")
public class TrainerController {

    List<Trainer> trainers = new ArrayList<>();

    @GetMapping
    public ResponseEntity<List<Trainer>> findAll() {
        if (trainers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(trainers);
    }
}
