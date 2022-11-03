package com.example.demo.file.parser;

import org.apache.poi.xwpf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FileParser {

    private List<Map<String, Object>> quizzes = new ArrayList<>();
    private void traversePictures(XWPFRun run, List<XWPFPicture> pictures) throws Exception {
        for (XWPFPicture picture : pictures) {
            //System.out.println(picture);

            /*String picturesNonVisualDrawingProps = getSummary(getNonVisualDrawingProps(getInlineOrAnchor(run, picture)));
            System.out.println(picturesNonVisualDrawingProps);*/

            XWPFPictureData pictureData = picture.getPictureData();
            System.out.println(pictureData);
        }
    }
    private Map<String, Object> traverseRunElements(List<IRunElement> runElements) throws Exception {
        Map<String, Object> elementValue = new HashMap<>();
        AtomicInteger i = new AtomicInteger(0);
        for (IRunElement runElement : runElements) {
            if (runElement instanceof XWPFFieldRun) {
                XWPFFieldRun fieldRun = (XWPFFieldRun)runElement;
                //System.out.println(fieldRun.getClass().getName());
                if(fieldRun.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i.getAndIncrement()), fieldRun.text().trim());
                traversePictures(fieldRun, fieldRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFHyperlinkRun) {
                XWPFHyperlinkRun hyperlinkRun = (XWPFHyperlinkRun)runElement;
                //System.out.println(hyperlinkRun.getClass().getName());
                if(hyperlinkRun.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i), hyperlinkRun.text().trim());
                traversePictures(hyperlinkRun, hyperlinkRun.getEmbeddedPictures());
            } else if (runElement instanceof XWPFRun) {
                XWPFRun run = (XWPFRun)runElement;
                //System.out.println(run.getClass().getName());
                if(run.text().equals(""))
                    break;
                elementValue.put(String.valueOf(i), run.text().trim());
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
    private Map<String, String> traverseTableCells(List<ICell> tableICells) throws Exception {
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

    private Map<String, Object> traverseTableRows(List<XWPFTableRow> tableRows) throws Exception {
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

    private Map<String, Object> traverseBodyElements(List<IBodyElement> bodyElements) throws Exception {
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

    private Map<String, Object> templateSeggregation() {
        AtomicInteger index = new AtomicInteger(0);
        Map<String, Object> assessmentMap = new HashMap<>();
        quizzes.forEach((template) -> {
            if(template.size() == 19) {
                List<Map<String, Object>> tempQuizList = new ArrayList<>();
                Map<String, Object> templateMap = new HashMap<>();
                templateMap.put("TemplateId", template.remove("Template ID"));
                templateMap.put("TemplateName", template.remove("Template Name"));
                templateMap.put("Quizzes", tempQuizList);
                tempQuizList.add(template);
                assessmentMap.put(String.valueOf(index.getAndIncrement()), templateMap);
            } else {
                int i = index.get() - 1;
                Map<String, Object> map = (Map<String, Object>) assessmentMap.get(String.valueOf(index.get() - 1));
                List<Map<String, Object>> quizList = (List<Map<String, Object>>) map.get("Quizzes");
                quizList.add(template);
            }
        });
        return assessmentMap;

    }

    public Map<String, Object> fileParser(File uploadedFile) throws Exception {
        XWPFDocument document = new XWPFDocument(new FileInputStream(uploadedFile));
        traverseBodyElements(document.getBodyElements());
        document.close();
        Map<String, Object> assessmentMap = templateSeggregation();
        return assessmentMap;
    }
}
