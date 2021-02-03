package com.app.service;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import com.app.dao.entity.Paper;
import com.app.dao.entity.Student;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class ResultPdfExporter {
	private ArrayList<Student> students;
	private Paper paper;
    
    public ResultPdfExporter(ArrayList<Student> students,Paper paper) {
        this.students = students;
        this.paper= paper;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
//        "studentId": 14,
//        "prn": "987123",
//        "marksObtained": 6,
//        "studentName": "AA",
//        "submittedOn": "2021-01-30T15:42:09.465"
        cell.setPhrase(new Phrase("Students ID", font));
         
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Students Name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("PRN", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Submitted On", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Marks Obtained", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
    	int sNo=0;
        for (Student student : students) {
            table.addCell(String.valueOf(++sNo));
            table.addCell(student.getStudentName());
            table.addCell(student.getPrn());
            table.addCell(student.getSubmittedOn().toString());
            table.addCell(String.valueOf(String.valueOf(student.getMarksObtained())));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLACK);
         
        Paragraph p = new Paragraph("Results", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);
        font = FontFactory.getFont(
        	    FontFactory.TIMES_ROMAN,
        	    Font.ITALIC);   
        font.setColor(Color.BLACK);
        font.setSize(14);
         
        Paragraph pp = new Paragraph("Paper Name  : "+paper.getPaperName()+"\n"
        		+"Paper Subject  : "+paper.getPaperSubject()+"\n"+"Difficulty Level  : "+paper.getDifficultyLevel()+"\n", font);        
        pp.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(pp);
        
        Paragraph ppp = new Paragraph("Total Marks  : "+paper.getTotalMarks(), font);        
        ppp.setAlignment(Paragraph.ALIGN_RIGHT);
        ppp.setSpacingAfter(20f);
        document.add(ppp);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
                 document.add(table);
         
        document.close();
         
    }
}
