package co.com.jdti.app.services;

import co.com.jdti.app.models.entities.InstructorEntity;
import co.com.jdti.app.models.repositories.IInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
}
