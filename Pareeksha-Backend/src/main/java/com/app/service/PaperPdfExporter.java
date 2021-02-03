package com.app.service;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.app.dao.entity.Paper;
import com.app.dao.entity.QuestionType;
import com.app.dto.ChoiceResponsedto;
import com.app.dto.QuestionResponsedto;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Service
public class PaperPdfExporter {
	private Paper paper;
	private ArrayList<QuestionResponsedto> questions =  new ArrayList<>();
    
    public Paper getPaper() {
		return paper;
	}
    
	public void setPaper(Paper paper) {
		this.paper = paper;
	}

	public ArrayList<QuestionResponsedto> getQuestion() {
		return questions;
	}

	public void setQuestion(ArrayList<QuestionResponsedto> questions) {
		this.questions = questions;
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(16);
        font.setColor(Color.BLACK);
         
        Paragraph n = new Paragraph("Paper Name :"+paper.getPaperName() , font);
        n.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(n);
        Paragraph s = new Paragraph("Paper Subject :"+paper.getPaperSubject() , font);
        s.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(s);
        Paragraph d = new Paragraph("Paper Duration :"+paper.getDuration() , font);
        d.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph l = new Paragraph();
        d.add("\n\n");
        document.add(d);
        l.setAlignment(Paragraph.ALIGN_LEFT);
        l.add("____________________________________________________________________________\n");
        l.add("____________________________________________________________________________\n");
        l.setSpacingAfter(10f);
        document.add(l);
        
        
        Font questionFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    	questionFont.setSize(12);
    	questionFont.setColor(Color.BLACK);
    	Font choiceFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
    	choiceFont.setSize(12);
    	choiceFont.setColor(Color.BLACK);
        int qNo = 1;
        for (QuestionResponsedto question : questions) {
        	Paragraph q = new Paragraph();
        	q.setFont(questionFont);
        	if (question.getQuestionType().equals(QuestionType.MATCHTHEFOLLOWING.toString())) {
				String matchTheFollowing[] = {};
				matchTheFollowing= question.getQuestion().split("~");
				q.add((qNo++)+"   Match the Following \n");
				q.add(matchTheFollowing[0]+"\b\b\b"+matchTheFollowing[4]+"\n");

				q.add(matchTheFollowing[1]+"\b\b\b"+matchTheFollowing[5]+"\n");
				q.add(matchTheFollowing[2]+"\b\b\b"+matchTheFollowing[6]+"\n");
				q.add(matchTheFollowing[3]+"\b\b\b"+matchTheFollowing[7]+"\n");
				document.add(q);
			}
        	else {
        		q.add((qNo++)+"   "+question.getQuestion());
                q.setAlignment(Paragraph.ALIGN_LEFT);
                q.setSpacingBefore(4.0f);
                document.add(q);
        	}
            int cNo = 1;
            for (ChoiceResponsedto choice : question.getChoices()) {           	
            	Paragraph c = new Paragraph((cNo++)+"   "+choice.getChoice(), choiceFont);
                c.setAlignment(Paragraph.ALIGN_LEFT);
                c.add("\n");
                document.add(c);
			}
            Paragraph ll = new Paragraph();
            ll.setAlignment(Paragraph.ALIGN_LEFT);
            ll.add("____________________________________________________________________________");
            ll.setSpacingAfter(10f);
            document.add(ll);
		}
        document.close();
         
    }
}
