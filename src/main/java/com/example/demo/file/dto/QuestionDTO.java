package com.example.demo.file.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDTO {
    private String id;
    private int serialNumber;
    private String type;
    private SubTopicDTO subTopic;
    private List<String> instructions;
    private String questionText;
    private List<OptionDTO> options;
    private List<String> answers;
    private String taxonomy;
    private String difficultyLevel;
    private List<String> pyq;
    private String wowFactor;
    private String qualityGrade;
    private String isGuided;
    private String primaryConcept;
    private Boolean status;
    private String solution;
    private List<String> exams;
    private List<String> conceptTips;
}
