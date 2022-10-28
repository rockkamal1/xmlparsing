package com.example.demo;

import java.io.File;

import javax.xml.stream.XMLStreamReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@SpringBootApplication
public class MagicEdTech12Application {

	public static void main(String[] args) {
		SpringApplication.run(MagicEdTech12Application.class, args);
		
		 try {
	         XmlMapper xmlMapper = new XmlMapper();
	         Employee pojo = xmlMapper.readValue(getXmlString(), Employee.class);
	         System.out.println(pojo);
	      } catch(Exception e) {
	         e.printStackTrace();
	      } 
	   }

	private static String getXmlString() {
		
		 return "<TemplateID>"
							+"<templateId>001</templateId>"
					 		+ " <templateName>MCQ</templateName>"
					 		+ " <topic>Congruence and Similarity</topic>"
					 		+ " <questionSerialNo>1</questionSerialNo>"
					 		+ " <subtopic>Introduction</subtopic>"
					 		+ " <instructions>None</instructions>"
					 		+ " <questionText>In given figure $\\mathrm{D}$ and $\\mathrm{E}$ are\n"
					 		+ "			respectively the points on the sides $\\mathrm{BA}$ and $\\mathrm{BC}$\n"
					 		+ "			of a $\\triangle \\mathrm{ABC}$ such that $\\mathrm{BD}=5 \\mathrm{~cm},\n"
					 		+ "			\\mathrm{BE}=4.2 \\mathrm{~cm}, \\mathrm{BA}=15 \\mathrm{~cm}$, and\n"
					 		+ "			$\\mathrm{DE} \\| \\mathrm{AC}$, find $\\mathrm{BC}$.</questionText>"
			                + "<options>A ) 26.4\n"
			                + "			B )13.2\n"
			                + "			C) 8.8\n"
			                + "			D) 12.6</options>"
			                + "<answer>D</answer>"
							+ "<solution> asdasok</solution>" 
                + "</TemplateID>";
	}
		
	}


