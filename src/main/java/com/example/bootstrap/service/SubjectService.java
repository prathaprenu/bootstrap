package com.example.bootstrap.service;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Subject;
import com.example.bootstrap.payload.SubjectRequest;
import com.example.bootstrap.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    public Subject addSubject(SubjectRequest subjectRequest) {

        Subject subject = new Subject();
        subject.setSubjectName(subjectRequest.getSubjectName());

        return subjectRepository.save(subject);
    }

    public Optional<Subject> findBySubjectId(long subjectId) {
        return subjectRepository.findById(subjectId);
    }

    public void deleteSubjectById(long subjectId) {
        Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new BadRequestException("Subject not found with ID: " + subjectId));
        subjectRepository.deleteById(subject.getId());
    }

    public Subject updateSubjectById(long subjectId, SubjectRequest subjectRequest) {
        Subject updateSubject = subjectRepository.findById(subjectId).orElseThrow(() -> new BadRequestException("Subject not exist with id: " + subjectId));

        updateSubject.setSubjectName(subjectRequest.getSubjectName());
        return subjectRepository.save(updateSubject);
    }

    public List<Subject> findAll() {
        return subjectRepository.findAll();
    }
}
