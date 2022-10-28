package com.example.demo;

public class Employee {
	
	private String templateId;
	   private String templateName;
	   private String topic;
	   private String questionSerialNo;
	   private String subtopic;
	   private String instructions;
	   private String questionText;
	   private String options;
	   private String answer;
	   private String solution;
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getQuestionSerialNo() {
		return questionSerialNo;
	}
	public void setQuestionSerialNo(String questionSerialNo) {
		this.questionSerialNo = questionSerialNo;
	}
	public String getSubtopic() {
		return subtopic;
	}
	public void setSubtopic(String subtopic) {
		this.subtopic = subtopic;
	}
	public String getInstructions() {
		return instructions;
	}
	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	@Override
	public String toString() {
		return "Employee [templateId=" + templateId + ", templateName=" + templateName + ", topic=" + topic
				+ ", questionSerialNo=" + questionSerialNo + ", subtopic=" + subtopic + ", instructions=" + instructions
				+ ", questionText=" + questionText + ", options=" + options + ", answer=" + answer + ", solution="
				+ solution + "]";
	}
	public Employee(String templateId, String templateName, String topic, String questionSerialNo, String subtopic,
			String instructions, String questionText, String options, String answer, String solution) {
		super();
		this.templateId = templateId;
		this.templateName = templateName;
		this.topic = topic;
		this.questionSerialNo = questionSerialNo;
		this.subtopic = subtopic;
		this.instructions = instructions;
		this.questionText = questionText;
		this.options = options;
		this.answer = answer;
		this.solution = solution;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	   
	   


}
