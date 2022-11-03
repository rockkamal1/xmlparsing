package com.example.demo;

import com.example.demo.file.model.Assessment;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MagicEdTech12Application {

	public static void main(String[] args) {
		SpringApplication.run(MagicEdTech12Application.class, args);
		
		 try {
	         ObjectMapper objectMapper = new ObjectMapper();
	         Assessment assessment = objectMapper.readValue(getXmlString(), Assessment.class);
	         System.out.println(assessment);
	      } catch(Exception e) {
	         e.printStackTrace();
	      } 
	   }

	private static String getXmlString() {
		
		 return "{\r\n  \"templateId\": \"001\",\r\n  \"templateName\": \"MCQ\",\r\n  \"quizzes\": [\r\n    {\r\n      \"topic\": \"Congruence and Similarity\",\r\n      \"questionSerialNo\": \"1\",\r\n      \"subtopic\": \"Introduction\",\r\n      \"instructions\": \"None\",\r\n      \"questionText\": \"In given figure $\\\\mathrm{D}$ and $\\\\mathrm{E}$ are respectively the points on the sides $\\\\mathrm{BA}$ and $\\\\mathrm{BC}$ of a $\\\\triangle \\\\mathrm{ABC}$ such that $\\\\mathrm{BD}=5 \\\\mathrm{~cm}, \\\\mathrm{BE}=4.2 \\\\mathrm{~cm}, \\\\mathrm{BA}=15 \\\\mathrm{~cm}$, and $\\\\mathrm{DE} \\\\| \\\\mathrm{AC}$, find $\\\\mathrm{BC}$.\",\r\n      \"options\": {\r\n        \"A\": \"26.4\",\r\n        \"B\": \"13.2\",\r\n        \"C\": \"8.8\",\r\n        \"D\": \"12.6\"\r\n      },\r\n      \"answer\": \"D\",\r\n      \"solution\": \"asdasok\"\r\n    },\r\n    {\r\n      \"topic\": \"Congruence and Similarity\",\r\n      \"questionSerialNo\": \"1\",\r\n      \"subtopic\": \"Introduction\",\r\n      \"instructions\": \"None\",\r\n      \"questionText\": \"In given figure $\\\\mathrm{D}$ and $\\\\mathrm{E}$ are respectively the points on the sides $\\\\mathrm{BA}$ and $\\\\mathrm{BC}$ of a $\\\\triangle \\\\mathrm{ABC}$ such that $\\\\mathrm{BD}=5 \\\\mathrm{~cm}, \\\\mathrm{BE}=4.2 \\\\mathrm{~cm}, \\\\mathrm{BA}=15 \\\\mathrm{~cm}$, and $\\\\mathrm{DE} \\\\| \\\\mathrm{AC}$, find $\\\\mathrm{BC}$.\",\r\n      \"options\": {\r\n        \"A\": \"26.4\",\r\n        \"B\": \"13.2\",\r\n        \"C\": \"8.8\",\r\n        \"D\": \"12.6\"\r\n      },\r\n      \"answer\": \"D\",\r\n      \"solution\": \"asdasok\"\r\n    }\r\n  ]\r\n}";
	}
		
	}


