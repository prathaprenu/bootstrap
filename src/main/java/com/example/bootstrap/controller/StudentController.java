package com.example.bootstrap.controller;

import com.example.bootstrap.exception.BadRequestException;
import com.example.bootstrap.model.Image;
import com.example.bootstrap.model.Marks;
import com.example.bootstrap.model.Student;
import com.example.bootstrap.model.Subject;
import com.example.bootstrap.payload.ApiResponse;
import com.example.bootstrap.payload.MarksRequest;
import com.example.bootstrap.payload.StudentRequest;
import com.example.bootstrap.payload.SubjectRequest;
import com.example.bootstrap.service.ImageService;
import com.example.bootstrap.service.MarksService;
import com.example.bootstrap.service.StudentService;
import com.example.bootstrap.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @Autowired
    private ImageService imageService;


    /*-------------Student Api----------------*/
    @PostMapping("/add_student")
    public ResponseEntity<?> student(@RequestBody StudentRequest studentRequest) {
        Student result = studentService.addStudent(studentRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Student register successfully"));
    }

    @GetMapping("/get_student")
    public List<Student> getStudent() {
        return studentService.findByStudentId();
    }

    @DeleteMapping("/delete_student")
    public ResponseEntity<?> deleteStudent(@RequestParam long studentId) {
        studentService.deleteStudentById(studentId);
        return ResponseEntity.ok(new ApiResponse(true,"Delete student success"));
    }

    @PutMapping("/update_student")
    public ResponseEntity<?> updateStudent(@RequestParam long studentId,@RequestBody StudentRequest studentRequest) {
        studentService.updateStudentById(studentId,studentRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Update student details successfully"));
    }


    /*------------Subject Api-----------*/
    @PostMapping("/add_subject")
    public ResponseEntity<?> subject(@RequestBody SubjectRequest subjectRequest) {
        Subject result = subjectService.addSubject(subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Subject register successfully"));
    }

    @GetMapping("/get_subject")
    public List<Subject> getSubject() {
        return subjectService.findBySubjectId();
    }

    @DeleteMapping("/delete_subject")
    public ResponseEntity<?> deleteSubject(@RequestParam long subjectId) {
        subjectService.deleteSubjectById(subjectId);
        return ResponseEntity.ok(new ApiResponse(true,"Delete subject success"));
    }

    @PutMapping("/update_subject")
    public ResponseEntity<?> updateSubject(@RequestParam long subjectId,@RequestBody SubjectRequest subjectRequest) {
        subjectService.updateSubjectById(subjectId,subjectRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Update subject details successfully"));
    }


    /*------------Marks Api-----------*/
    @PostMapping("/add_marks")
    public ResponseEntity<?> marks(@RequestBody MarksRequest marksRequest) {
        Marks result = marksService.addMarks(marksRequest);
        return ResponseEntity.ok(new ApiResponse(true, "Marks added successfully"));
    }

    @GetMapping("/get_marks")
    public List<Marks> getStudentMarks(@RequestParam long studentId) {
        return marksService.findByStudentId(studentId);
    }

    @DeleteMapping("/delete_marks")
    public ResponseEntity<?> delete(@RequestParam long marksId) {
        marksService.deleteMarksById(marksId);
        return ResponseEntity.ok(new ApiResponse(true,"Delete marks success"));
    }

    @PutMapping("/update_marks")
    public ResponseEntity<?> update(@RequestParam long marksId,@RequestBody MarksRequest marksRequest) {
        marksService.updateMarksById(marksId,marksRequest);
        return ResponseEntity.ok(new ApiResponse(true,"Update marks details successfully"));
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

    /*-------------Image--------------*/
    @PostMapping("/add_image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            imageService.saveImage(file.getOriginalFilename(), file.getBytes());
            return ResponseEntity.ok("Image uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        }
    }

    @GetMapping("/get_image")
    public ResponseEntity<byte[]> getImage(@RequestParam Long id) {
        Optional<Image> optionalImage = imageService.getImageById(id);

        if (optionalImage.isPresent()) {
            Image image = optionalImage.get();
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image.getImageData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
