package co.com.jdti.app.services;

import co.com.jdti.app.models.entities.InstructorEntity;

import java.util.List;

public interface IInstructorServices {

    List<InstructorEntity> findAllInstructors();
}
