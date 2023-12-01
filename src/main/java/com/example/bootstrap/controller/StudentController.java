package com.example.bootstrap.controller;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Marks;
import com.example.bootstrap.model.Student;
import com.example.bootstrap.model.Subject;
import com.example.bootstrap.payload.ApiResponse;
import com.example.bootstrap.payload.MarksRequest;
import com.example.bootstrap.payload.StudentRequest;
import com.example.bootstrap.payload.SubjectRequest;
import com.example.bootstrap.service.MarksService;
import com.example.bootstrap.service.StudentService;
import com.example.bootstrap.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/details")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private MarksService marksService;


    /*-------------Student Api----------------*/
    @PostMapping("/add_student")
    public ResponseEntity<?> student(@RequestBody StudentRequest studentRequest) {
        Student result = studentService.addStudent(studentRequest);
        return ResponseEntity.ok(new ApiResponse(true, "student register successfully"));
    }

    @GetMapping("/get_student")
    public List<Student> getStudent() {
        return studentService.findByStudentId();
    }

    @DeleteMapping("/delete_student")
    public ResponseEntity<?> deleteStudent(@RequestParam long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok(new ApiResponse(true,"delete student success"));
    }

    @PutMapping("/update_student")
    public ResponseEntity<?> updateStudent(@RequestParam long studentId,@RequestBody StudentRequest studentRequest) {
        studentService.updateStudentById(studentId,studentRequest);
        return ResponseEntity.ok(new ApiResponse(true,"update student details successfully"));
    }


    /*------------Subject Api-----------*/
    @PostMapping("/add_subject")
    public ResponseEntity<?> subject(@RequestBody SubjectRequest subjectRequest) {
        Subject result = subjectService.addSubject(subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"subject register successfully"));
    }

    @GetMapping("/get_subject")
    public List<Subject> getSubject() {
        return subjectService.findBySubjectId();
    }

    @DeleteMapping("/delete_subject")
    public ResponseEntity<?> deleteSubject(@RequestParam long subjectId) {
        subjectService.deleteSubjectById(subjectId);
        return ResponseEntity.ok(new ApiResponse(true,"delete subject success"));
    }

    @PutMapping("/update_subject")
    public ResponseEntity<?> updateSubject(@RequestParam long subjectId,@RequestBody SubjectRequest subjectRequest) {
        subjectService.updateSubjectById(subjectId,subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"update subject details successfully"));
    }


    /*------------Marks Api-----------*/
    @PostMapping("/add_marks")
    public ResponseEntity<?> marks(@RequestBody MarksRequest marksRequest) {
        Marks result = marksService.addMarks(marksRequest);
        return ResponseEntity.ok(new ApiResponse(true, "marks added successfully"));
    }

    @GetMapping("/get_marks")
    public List<Marks> getStudentMarks(@RequestParam long studentId) {
        return marksService.findByStudentId(studentId);
    }

    @DeleteMapping("/delete_marks")
    public ResponseEntity<?> delete(@RequestParam long marksId) {
        marksService.deleteMarksById(marksId);
        return ResponseEntity.ok(new ApiResponse(true,"delete marks success"));
    }

    @PutMapping("/update_marks")
    public ResponseEntity<?> update(@RequestParam long marksId,@RequestBody MarksRequest marksRequest) {
        marksService.updateMarksById(marksId,marksRequest);
        return ResponseEntity.ok(new ApiResponse(true,"update marks details successfully"));
    }


    /*-------------Total Marks--------------*/
    @GetMapping("/student_total_marks")
    public ResponseEntity<Map<String, Object>> getStudentTotalMarks(@RequestParam long studentId) {
        Map<String, Object> response = new HashMap<>();

        int totalMarks = studentService.getStudentTotalMarks(studentId);
        Optional<Student> studentDetails = studentService.getStudentDetails(studentId);
        List<Subject> allSubjects = studentService.getAllSubjects();

        response.put("totalMarks", totalMarks);
        response.put("studentDetails", studentDetails.orElse(null));
        response.put("allSubjects", allSubjects);

        return ResponseEntity.ok(response);
    }

}
