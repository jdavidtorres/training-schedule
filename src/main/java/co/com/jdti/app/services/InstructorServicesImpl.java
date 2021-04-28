package co.com.jdti.app.services;

import co.com.jdti.app.models.entities.EventEntity;
import co.com.jdti.app.models.entities.InstructorEntity;
import co.com.jdti.app.models.repositories.IInstructorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class InstructorServicesImpl implements IInstructorServices {

    @Autowired
    private IInstructorRepository iInstructorRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InstructorEntity> findAllInstructors() {
        return iInstructorRepository.findAll();
    }

    @Transactional
    @Override
    public InstructorEntity save(InstructorEntity instructor) {
        return iInstructorRepository.save(instructor);
    }

    @Transactional
    @Override
    public InstructorEntity assignEvent(InstructorEntity instructorWithEvent) {
        return iInstructorRepository.save(instructorWithEvent);
    }

    @Override
    public Optional<InstructorEntity> findInstructorById(String idInstructor) {
        return iInstructorRepository.findById(idInstructor);
    }

    @Override
    public Long overallDuration(InstructorEntity instructor) {
        var daysT = 0L;
        var minutesT = 0L;
        List<EventEntity> events = instructor.getEvents();
        for (EventEntity event : events) {
            var dateInit = event.getStart();
            var instantInit = dateInit.toInstant();
            var dateEnd = event.getEnd();
            var instantEnd = dateEnd.toInstant();

            long minutes = ChronoUnit.MINUTES.between(instantInit.atZone(ZoneId.systemDefault()).toLocalDateTime(), instantEnd.atZone(ZoneId.systemDefault()).toLocalDateTime().plusDays(1));

            minutesT += minutes;
        }
        daysT = TimeUnit.DAYS.convert(minutesT, TimeUnit.MINUTES);
        return daysT;
    }
}
