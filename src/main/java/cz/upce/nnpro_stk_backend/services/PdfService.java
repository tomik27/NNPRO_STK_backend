package cz.upce.nnpro_stk_backend.services;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.logging.Logger;


public class PdfService {
    //public Logger logger = LoggerFactory.getLogger(PdfService.class);
    public void createPdf(){
        String title="Welcome";
        String content="We provide technical";
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document();

        try{
            PdfWriter.getInstance(document,out);
        }catch (DocumentException e){

        }
    }
}
