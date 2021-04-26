package co.com.jdti.app.services;

import co.com.jdti.app.models.entities.InstructorEntity;
import co.com.jdti.app.models.repositories.IInstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorServicesImpl implements IInstructorServices {

    @Autowired
    private IInstructorRepository iInstructorRepository;

    @Override
    public List<InstructorEntity> findAllInstructors() {
        return iInstructorRepository.findAll();
    }
}
