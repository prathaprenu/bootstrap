package com.example.bootstrap.service;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Marks;
import com.example.bootstrap.model.Student;
import com.example.bootstrap.model.Subject;
import com.example.bootstrap.payload.StudentRequest;
import com.example.bootstrap.repository.MarksRepository;
import com.example.bootstrap.repository.StudentRepository;
import com.example.bootstrap.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private MarksRepository marksRepository;

    public Student addStudent(StudentRequest studentRequest) {

        Student student = new Student();
        student.setName(studentRequest.getName());
        student.setGender(studentRequest.getGender());
        student.setPhoneNumber(studentRequest.getPhoneNumber());

        return studentRepository.save(student);
    }

    public List<Student> findByStudentId() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(long studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student not found with ID: " + studentId));
        studentRepository.deleteById(student.getId());
    }

    public Student updateStudentById(long studentId, StudentRequest studentRequest) {

        Student updateStudent = studentRepository.findById(studentId).orElseThrow(() -> new BadRequestException("Student not exist with id: " + studentId));

        updateStudent.setName(studentRequest.getName());
        updateStudent.setGender(studentRequest.getGender());
        updateStudent.setPhoneNumber(studentRequest.getPhoneNumber());

        return studentRepository.save(updateStudent);
    }

    public int getStudentTotalMarks(Long studentId) {
        List<Marks> marksList = marksRepository.findByStudentId(studentId);
        return marksList.stream().mapToInt(Marks::getMarks).sum();
    }

    public Optional<Student> getStudentDetails(long studentId) {
        return studentRepository.findById(studentId);
    }

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
}
