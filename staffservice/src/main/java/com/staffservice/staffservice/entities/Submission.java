package com.staffservice.staffservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "submission")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int submissionId;
    @Column(name = "submission_text")
    private String submissionText;
    @Column(name = "submission_date")
    private String submissionDate;
    @Column(name = "submission_status")
    private SubmissionStatus submissionStatus;
    @Column(name = "class_name")
    private String className;
    @Column(name = "student_id")
    private long studentAdmissionId;
    @Column(name = "assignment_id")
    private long assignmentId;
}
