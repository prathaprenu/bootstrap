package com.example.bootstrap.payload;

import javax.validation.constraints.NotBlank;

public class SubjectRequest {

    @NotBlank
    private String subjectName;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
