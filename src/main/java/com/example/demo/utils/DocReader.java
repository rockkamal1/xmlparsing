package com.example.demo.utils;

import com.example.demo.file.handler.FileParserHandler;
import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DocReader {
    private static List<Map<String, Object>> quizzes = new ArrayList<>();

    static org.apache.xmlbeans.XmlObject getInlineOrAnchor(org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture ctPictureToFind, org.apache.xmlbeans.XmlObject inlineOrAnchor) {
        String declareNameSpaces = "declare namespace pic='http://schemas.openxmlformats.org/drawingml/2006/picture'; ";
        org.apache.xmlbeans.XmlObject[] selectedObjects = inlineOrAnchor.selectPath(
                declareNameSpaces
                        + "$this//pic:pic");
        for (org.apache.xmlbeans.XmlObject selectedObject : selectedObjects) {
            if (selectedObject instanceof org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture) {
                org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture ctPicture = (org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture)selectedObject;
                if (ctPictureToFind.equals(ctPicture)) {
                    // this is the inlineOrAnchor for that picture
                    return inlineOrAnchor;
                }
            }
        }
        return null;
    }

    static org.apache.xmlbeans.XmlObject getInlineOrAnchor(XWPFRun run, XWPFPicture picture) {
        org.openxmlformats.schemas.drawingml.x2006.picture.CTPicture ctPictureToFind = picture.getCTPicture();
        for (org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDrawing drawing : run.getCTR().getDrawingList()) {
            for (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline inline : drawing.getInlineList()) {
                org.apache.xmlbeans.XmlObject inlineOrAnchor = getInlineOrAnchor(ctPictureToFind, inline);
                // if inlineOrAnchor is not null, then this is the inline for that picture
                if (inlineOrAnchor != null) return inlineOrAnchor;
            }
            for (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor anchor : drawing.getAnchorList()) {
                org.apache.xmlbeans.XmlObject inlineOrAnchor = getInlineOrAnchor(ctPictureToFind, anchor);
                // if inlineOrAnchor is not null, then this is the anchor for that picture
                if (inlineOrAnchor != null) return inlineOrAnchor;
            }
        }
        return null;
    }

    static org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps getNonVisualDrawingProps(org.apache.xmlbeans.XmlObject inlineOrAnchor) {
        if (inlineOrAnchor == null) return null;
        if (inlineOrAnchor instanceof org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline) {
            org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline inline = (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTInline)inlineOrAnchor;
            return inline.getDocPr();
        } else if (inlineOrAnchor instanceof org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor) {
            org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor anchor = (org.openxmlformats.schemas.drawingml.x2006.wordprocessingDrawing.CTAnchor)inlineOrAnchor;
            return anchor.getDocPr();
        }
        return null;
    }

    static String getSummary(org.openxmlformats.schemas.drawingml.x2006.main.CTNonVisualDrawingProps nonVisualDrawingProps) {
        if (nonVisualDrawingProps == null) return "";
        String summary = "Id:=" + nonVisualDrawingProps.getId();
        summary += " Name:=" + nonVisualDrawingProps.getName();
        summary += " Title:=" + nonVisualDrawingProps.getTitle();
        summary += " Descr:=" + nonVisualDrawingProps.getDescr();
        return summary;
    }

    static void traversePictures(XWPFRun run, List<XWPFPicture> pictures) throws Exception {
        for (XWPFPicture picture : pictures) {
            //System.out.println(picture);

            /*String picturesNonVisualDrawingProps = getSummary(getNonVisualDrawingProps(getInlineOrAnchor(run, picture)));
            System.out.println(picturesNonVisualDrawingProps);*/

            XWPFPictureData pictureData = picture.getPictureData();
            System.out.println(pictureData);
        }
    }
    static Map<String, Object> traverseRunElements(List<IRunElement> runElements) throws Exception {
        Map<String, Object> elementValue = new HashMap<>();
        AtomicInteger i = new AtomicInteger(0);
        for (IRunElement runElement : runElements) {
            if (runElement instanceof XWPFFieldRun) {
                XWPFFieldRun fieldRun = (XWPFFieldRun)runElement;
                //System.out.println(fieldRun.getClass().getName());
                if(fieldRun.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i.getAndIncrement()), fieldRun.text());
                traversePictures(fieldRun, fieldRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFHyperlinkRun) {
                XWPFHyperlinkRun hyperlinkRun = (XWPFHyperlinkRun)runElement;
                //System.out.println(hyperlinkRun.getClass().getName());
                if(hyperlinkRun.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i), hyperlinkRun.text());
                traversePictures(hyperlinkRun, hyperlinkRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFRun) {
                XWPFRun run = (XWPFRun)runElement;
                //System.out.println(run.getClass().getName());
                if(run.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i), run.text());
                traversePictures(run, run.getEmbeddedPictures());
            } else if (runElement instanceof XWPFSDT) {
                XWPFSDT sDT = (XWPFSDT)runElement;
                System.out.println(sDT);
                //System.out.println(sDT.getContent());
                //ToDo: The SDT may have traversable content too.
            }
        }
        return elementValue;
    }
    static Map<String, String> traverseTableCells(List<ICell> tableICells) throws Exception {
        List<String> cellValues = new ArrayList<>();
        Map<String, String> assessment = new HashMap<>();
        for (ICell tableICell : tableICells) {
            if (tableICell instanceof XWPFSDTCell) {
                XWPFSDTCell sDTCell = (XWPFSDTCell)tableICell;
                //System.out.println(sDTCell);
                //ToDo: The SDTCell may have traversable content too.
            } else if (tableICell instanceof XWPFTableCell) {
                XWPFTableCell tableCell = (XWPFTableCell)tableICell;
                //System.out.println(tableCell);
                cellValues.addAll(traverseBodyElements(tableCell.getBodyElements())
                        .values().stream().map(String::valueOf).collect(Collectors.toList()));
            }
        }

        if(tableICells.size() == 2) {
            assessment.put(cellValues.remove(0),
                    cellValues.stream().map(String::valueOf).collect(Collectors.joining(" ")));
        }

        return assessment;
    }

    static Map<String, Object> traverseTableRows(List<XWPFTableRow> tableRows) throws Exception {
        Map<String, Object> assessment = new HashMap<>();
        Map<String, String> options = new HashMap<>();
        boolean nonTwoCellsRows = false;
        String temp = "";
        for (XWPFTableRow tableRow : tableRows) {
            List<ICell> tableICells = tableRow.getTableICells();
            if(tableICells.size() == 2) {
                nonTwoCellsRows = false;
                traverseTableCells(tableICells)
                        .forEach((k, v) -> assessment.put(k, v));
            } else {
                if(!nonTwoCellsRows) {
                XWPFTableCell tableCell = (XWPFTableCell)tableICells.get(0);
                temp = traverseBodyElements(tableCell.getBodyElements()).values().stream().map(String::valueOf)
                        .collect(Collectors.toList()).get(0);
                }
                nonTwoCellsRows = true;
                tableICells.remove(0);
                traverseTableCells(tableICells)
                        .forEach((k, v) -> options.put(k, v));
                if(!assessment.containsKey(temp)) {
                    assessment.put(temp, new HashMap<>());
                } else {
                    Map<String, Object> tempMap = (Map<String, Object>) assessment.get(temp);
                    options.forEach((k, v) -> tempMap.put(k, v));
                }
            }

            if(!nonTwoCellsRows) {
                temp = "";
            }
        }
        return assessment;
    }

    static Map<String, Object> traverseBodyElements(List<IBodyElement> bodyElements) throws Exception {
        Map<String, Object> elementMap = new HashMap<>();
        AtomicInteger i = new AtomicInteger(0);
        for (IBodyElement bodyElement : bodyElements) {
            if (bodyElement instanceof XWPFParagraph) {
                XWPFParagraph paragraph = (XWPFParagraph)bodyElement;
                Map<String, Object> tempMap = traverseRunElements(paragraph.getIRuns());
                if(tempMap.size() == 0) {
                    continue;
                }
                tempMap.forEach((k,v) -> elementMap.put(String.valueOf(i.getAndIncrement()), v));
            } else if (bodyElement instanceof XWPFSDT) {
                XWPFSDT sDT = (XWPFSDT)bodyElement;
                //System.out.println(sDT);
                //System.out.println(sDT.getContent());
                //ToDo: The SDT may have traversable content too.
                return elementMap;
            } else if (bodyElement instanceof XWPFTable) {
                XWPFTable table = (XWPFTable)bodyElement;
                Map<String, Object> quiz = traverseTableRows(table.getRows());
                quizzes.add(quiz);
                quiz.forEach((k,v) -> elementMap.put(String.valueOf(i.getAndIncrement()),v));
            }
        }
        return elementMap;
    }

    static Map<String, Object> templateSegregation() {
        AtomicInteger index = new AtomicInteger(0);
        Map<String, Object> assessmentMap = new HashMap<>();
        quizzes.forEach((template) -> {
            if(template.size() == 19) {
                List<Map<String, Object>> tempQuizList = new ArrayList<>();
                Map<String, Object> templateMap = new HashMap<>();
                templateMap.put("TemplateId", template.remove("Template ID"));
                templateMap.put("TemplateName", template.remove("Template Name"));
                templateMap.put("quizzes", tempQuizList);
                tempQuizList.add(template);
                assessmentMap.put(String.valueOf(index.getAndIncrement()), templateMap);
            } else {
                int i = index.get() - 1;
                Map<String, Object> map = (Map<String, Object>) assessmentMap.get(String.valueOf(index.get() - 1));
                List<Map<String, Object>> quizList = (List<Map<String, Object>>) map.get("quizzes");
                quizList.add(template);
            }
        });
        return assessmentMap;
    }

    public static void main(String[] args) throws Exception {
        String inFilePath = "/Users/atulkumar/development-workspace/pocs/xmlparsing/src/main/resources/static/Examfactor_Samples_Questions.docx";
        File uploadedFile = new File(inFilePath);
        FileParserHandler fileParserHandler = new FileParserHandler();
        String json = fileParserHandler.getTemplateQuestions(uploadedFile);
        System.out.println(json);
    }
}
