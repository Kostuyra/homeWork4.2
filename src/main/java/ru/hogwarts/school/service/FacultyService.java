package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.NotFoundException;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        if (faculty == null){
            throw new NotFoundException();
        }
        return faculty;
    }

    public Faculty updateFaculty(Faculty faculty) {
        if (facultyRepository.findById(faculty.getId()) == null) {
            throw new NotFoundException();
        }
        facultyRepository.save(faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        Faculty faculty = facultyRepository.findById(id).get();
        if (faculty == null) {
            throw new NotFoundException();
        }
        facultyRepository.deleteById(id);
        return faculty;
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll().stream().collect(Collectors.toList());
    }
    public List<Faculty> FacultiesByNameIgnoreCaseOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findFacultiesByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }
}
