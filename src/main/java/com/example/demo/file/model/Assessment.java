package com.example.demo.file.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assessment implements Serializable {
	@JsonAlias("TemplateId")
	private String templateId;
	@JsonAlias("TemplateName")
	private String templateName;
	@JsonAlias("Quizzes")
	List<Quiz> quizzes;
}
