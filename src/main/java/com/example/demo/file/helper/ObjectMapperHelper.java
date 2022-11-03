package com.example.demo.file.helper;

import com.example.demo.file.model.Assessment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapperHelper {

    public static String covertMapToJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

    public static Map<String, Assessment> getJsonObjectFromJsonString(String jsonString) throws JsonProcessingException {
        return new ObjectMapper().readValue(jsonString, new TypeReference<HashMap<String,Assessment>>() {});
    }
}
