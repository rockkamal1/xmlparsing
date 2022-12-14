package com.example.demo.file.handler;

import com.example.demo.file.dto.QuestionDTO;
import com.example.demo.file.model.Assessment;
import com.example.demo.file.model.Quiz;
import com.example.demo.file.parser.FileParser;
import com.example.demo.file.helper.ObjectMapperHelper;
import com.example.demo.file.mapper.QuestionDTOMapper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileParserHandler {

    public String getTemplateQuestions(File file) throws Exception {
        Map<String, Assessment> assessmentMap = prepareAssessmentMapFromTemplateJson(file);
        List<QuestionDTO> questionList = getQuestionDTOList(assessmentMap);

        String questionJson = ObjectMapperHelper.covertMapToJsonString(questionList);
        return questionJson;
    }

    private String getParsedTemplateJson(File file) throws Exception {
        FileParser fileParser = new FileParser();
        Map<String, Object> assessmentMap = fileParser.fileParser(file);
        String assessmentJson = ObjectMapperHelper.covertMapToJsonString(assessmentMap);

        return assessmentJson;
    }

    private Map<String, Assessment> prepareAssessmentMapFromTemplateJson(File file) throws Exception {
        String templateJson = getParsedTemplateJson(file);
        Map<String, Assessment> assessmentMap = ObjectMapperHelper.getJsonObjectFromJsonString(templateJson);
        return assessmentMap;
    }

    private List<QuestionDTO> getQuestionDTOList(Map<String, Assessment> assessmentMap) {
        List<Quiz> quizList = new ArrayList<>();
        assessmentMap.forEach((k, v) -> {
            quizList.addAll(v.getQuizzes());
        });

        List<QuestionDTO> questionDTOList = quizList.stream()
                .map(QuestionDTOMapper::getQuestionDTO)
                .collect(Collectors.toList());

        return questionDTOList;
    }
}
