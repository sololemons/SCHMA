package com.staffservice.staffservice.dtos;

import com.staffservice.staffservice.entities.Assignments;

import java.util.stream.Collectors;

public class AssignmentMapper {

    public static AssignmentDto toDto(Assignments assignment) {
        AssignmentDto dto = new AssignmentDto();
        dto.setAssignmentId(assignment.getAssignmentId());
        dto.setTitle(assignment.getTitle());
        dto.setCreationDate(assignment.getCreationDate());
        dto.setDescription(assignment.getDescription());


        dto.setQuestions(
                assignment.getQuestions()
                        .stream()
                        .map(q -> new QuestionDto(q.getQuestionId(), q.getQuestionText(), String.valueOf(q.getQuestionType()),q.getOptions()))
                        .collect(Collectors.toList())
        );

        return dto;
    }
}
