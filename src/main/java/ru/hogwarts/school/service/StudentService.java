package ru.hogwarts.school.service;


import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.NotFoundException;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudent(long id) {
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            throw new NotFoundException();
        }
        return student;
    }

    public Student updateStudent(Student student) {
        if (studentRepository.findById(student.getId()) == null) {
            throw new NotFoundException();
        }
        studentRepository.save(student);
        return student;
    }

    public Student deleteStudent(long id) {
        Student student = studentRepository.findById(id).get();
        if (student == null) {
            throw new NotFoundException();
        }
        studentRepository.deleteById(id);
        return student;
    }

    public List<Student> getAll() {
        return studentRepository.findAll().stream().collect(Collectors.toList());
    }

    public List<Student> filterStudentsByAge(int age) {
        return studentRepository.
                findStudentsByAge(age).
                stream().
                collect(Collectors.toList());
    }

    public List<Student> findStudentsByAgeBetween(int minAge, int maxAge) {
        return studentRepository.findByAgeBetween(minAge, maxAge);
    }


    public Integer getStudentsCount() {
        return studentRepository.getStudentsCount();
    }


    public Float getAvgAgeStudents() {
        return studentRepository.getAvgAgeStudents();
    }


    public List<Student> getLastFive() {
        return studentRepository.getLastFive();
    }

}
