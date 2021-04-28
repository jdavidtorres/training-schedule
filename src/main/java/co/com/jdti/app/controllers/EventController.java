package co.com.jdti.app.controllers;

import co.com.jdti.app.models.entities.EventEntity;
import co.com.jdti.app.services.IEventServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin({"*"})
@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private IEventServices iEventServices;

    @GetMapping
    public ResponseEntity<List<EventEntity>> findAll() {
        List<EventEntity> eventEntities = iEventServices.findAll();
        if (eventEntities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(eventEntities);
    }

    @PutMapping
    public ResponseEntity<EventEntity> edit(@Valid @RequestBody EventEntity event, BindingResult result) {
        if (iEventServices.findEventById(event.getId()).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(iEventServices.edit(event));
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(String idEvent) {
        if (iEventServices.findEventById(idEvent).isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        iEventServices.delete(idEvent);
        return ResponseEntity.ok().build();
    }
}
