package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias("Topic")
    private String topic;
    @JsonAlias("Question Serial No")
    private String questionSerialNo;
    @JsonAlias("Subtopic")
    private String subTopic;
    @JsonAlias("Instructions")
    private String instructions;
    @JsonAlias("Question Text")
    private String questionText;
    @JsonAlias({"Options", "options"})
    private Map<String, Object> options;
    @JsonAlias("Answer")
    private String answer;
    @JsonAlias("Solution")
    private String solution;
    @JsonAlias("Exams")
    private String exams;
    @JsonAlias("Question Type")
    private String questionType;
    @JsonAlias("Taxonomy")
    private String taxonomy;
    @JsonAlias("Difficulty Level")
    private String difficultyLevel;
    @JsonAlias("PYQ")
    private String pyq;
    @JsonAlias("Wow Factor")
    private String wowFactor;
    @JsonAlias("Quality Grade")
    private String qualityGrade;
    @JsonAlias("Remarks")
    private String remarks;
    @JsonAlias("Concept Tips")
    private String conceptTips;
}
