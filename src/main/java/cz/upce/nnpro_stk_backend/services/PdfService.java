package cz.upce.nnpro_stk_backend.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;

@Service
public class PdfService {
    //public Logger logger = LoggerFactory.getLogger(PdfService.class);
    public ByteArrayInputStream createPdf(){
        String title="Welcome";
        String content="We provide technical";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();
        PdfWriter.getInstance(document,out);

        document.open();

        Font tittleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,25);
        Paragraph titlePara = new Paragraph(title,tittleFont);
        titlePara.setAlignment(Element.ALIGN_CENTER);
        document.add(titlePara);

        Font paraFont = FontFactory.getFont(FontFactory.HELVETICA,18);
        Paragraph paragraph = new Paragraph(content);
        paragraph.add(new Chunk("Wow this is totally shit"));
        document.add(paragraph);

        document.close();

        try{
        }catch (DocumentException e){

        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
