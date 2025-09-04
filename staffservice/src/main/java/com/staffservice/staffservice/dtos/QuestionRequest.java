package com.staffservice.staffservice.dtos;

import com.staffservice.staffservice.entities.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionRequest {
    private String questionText;
    private String type;
    private List<String>options;
    private String correctAnswer;
}
