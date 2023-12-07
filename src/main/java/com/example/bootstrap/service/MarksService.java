package com.example.bootstrap.service;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Marks;
import com.example.bootstrap.model.Student;
import com.example.bootstrap.model.Subject;
import com.example.bootstrap.payload.MarksRequest;
import com.example.bootstrap.repository.MarksRepository;
import com.example.bootstrap.repository.StudentRepository;
import com.example.bootstrap.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    private MarksRepository marksRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public Marks addMarks(MarksRequest marksRequest) {
        Student student = studentRepository.findById(marksRequest.getStudent_id()).orElseThrow(()->new BadRequestException("student not found"));
        Subject subject = subjectRepository.findById(marksRequest.getSubject_id()).orElseThrow(()->new BadRequestException("subject not found"));

        Marks mark = new Marks();
        mark.setMarks(marksRequest.getMarks());
        mark.setStudent(student);
        mark.setSubject(subject);

        return marksRepository.save(mark);
    }

    public List<Marks> findByStudentId(long studentId) {
        return marksRepository.findByStudentId(studentId);
    }

    public void deleteMarksById(long marksId) {
        Marks marks = marksRepository.findById(marksId).orElseThrow(() -> new BadRequestException("Marks not found with ID: " + marksId));
        marksRepository.deleteById(marks.getId());
    }

    public Marks updateMarksById(long marksId,MarksRequest marksRequest) {
        Student student = studentRepository.findById(marksRequest.getStudent_id()).orElseThrow(()->new BadRequestException("student not found"));
        Subject subject = subjectRepository.findById(marksRequest.getSubject_id()).orElseThrow(()->new BadRequestException("subject not found"));

        Marks updateMarks = marksRepository.findById(marksId).orElseThrow(() -> new BadRequestException("Marks not exist with id: " + marksId));

        updateMarks.setMarks(marksRequest.getMarks());
        updateMarks.setStudent(student);
        updateMarks.setSubject(subject);

        return marksRepository.save(updateMarks);

    }

    public List<Marks> findAll() {
        return marksRepository.findAll();
    }
}
