package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz implements Serializable {
    private String topic;
    private String questionSerialNo;
    private String subtopic;
    private String instructions;
    private String questionText;
    private Map<String, String> options;
    private String answer;
    private String solution;
    private String exams;
    private String taxonomy;
    private String difficultyLevel; // todo: convert it into Enum
    private String PYQ;
    private String wowFactor;
    private char qualityGrade;
    private String remarks;
}
