package com.example.demo.file.mapper;

import com.example.demo.file.dto.OptionDTO;
import com.example.demo.file.dto.QuestionDTO;
import com.example.demo.file.dto.SubTopicDTO;
import com.example.demo.file.model.Quiz;

import java.util.*;

public class QuestionDTOMapper {

    public static QuestionDTO getQuestionDTO(Quiz quiz) {
        List<OptionDTO> optionDTOList = new ArrayList<>();
        quiz.getOptions().forEach((k, v) -> {
            optionDTOList.add(OptionDTO.builder()
                    .option(k)
                    .value(String.valueOf(v))
                    .optionType("text").build());
        });

        return QuestionDTO.builder()
                .id(UUID.randomUUID().toString())
                .serialNumber(Integer.valueOf(quiz.getQuestionSerialNo()))
                .type(quiz.getQuestionType())
                .subTopic(SubTopicDTO.builder()
                        .id(UUID.randomUUID().toString())
                        .name(quiz.getSubTopic())
                        .build())
                .instructions(Arrays.asList(quiz.getInstructions()))
                .questionText(quiz.getQuestionText())
                .answers(Arrays.asList(quiz.getAnswer()))
                .solution(quiz.getSolution())
                .exams(Arrays.asList(quiz.getExams()))
                .conceptTips(Arrays.asList(quiz.getConceptTips()))
                .taxonomy(quiz.getTaxonomy())
                .difficultyLevel(quiz.getDifficultyLevel())
                .pyq(Arrays.asList(quiz.getPyq()))
                .wowFactor(quiz.getWowFactor())
                .qualityGrade(quiz.getQualityGrade())
                .isGuided("Yes") //check the template for value
                .primaryConcept(quiz.getConceptTips())
                .options(optionDTOList)
                .build();
    }

}
