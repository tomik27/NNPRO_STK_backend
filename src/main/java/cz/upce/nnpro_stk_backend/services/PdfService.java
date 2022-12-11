package cz.upce.nnpro_stk_backend.services;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfWriter;
import cz.upce.nnpro_stk_backend.dtos.CarFromCrvDto;
import cz.upce.nnpro_stk_backend.dtos.OwnerInCarDto;
import cz.upce.nnpro_stk_backend.entities.Car;
import cz.upce.nnpro_stk_backend.entities.FaultOfInspection;
import cz.upce.nnpro_stk_backend.entities.Inspection;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Set;
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

    public ByteArrayInputStream createPdf2(Inspection inspection, CarFromCrvDto carFromCrvDto)  {
        Car car = inspection.getCar();
        String name="Tomas Slaby";
        if(carFromCrvDto!=null) {
            if (carFromCrvDto.getOwners() != null) {
                OwnerInCarDto ownerInCarDto = carFromCrvDto.getOwners().get(0);
                 name = ownerInCarDto.getFirstName() + ownerInCarDto.getLastName();
            }
        }
        int a=0;
        int b=0;
        int c=0;
        Set<FaultOfInspection> faultInspections = inspection.getFaultInspections();
        for (FaultOfInspection fault:  faultInspections) {
            String designation = fault.getFault().getTypeOfFault().getDesignation();
                 if(designation.equals("A"))
                     a++;
                 else if(designation.equals("B"))
                     b++;
                 else if(designation.equals("C"))
                     c++;

        }
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document,out);

            // various fonts
            BaseFont bf_helv = BaseFont.createFont(BaseFont.HELVETICA, "Cp1252", false);
            BaseFont bf_times = BaseFont.createFont(BaseFont.TIMES_ROMAN, "Cp1252", false);
            BaseFont bf_courier = BaseFont.createFont(BaseFont.COURIER, "Cp1252", false);
            BaseFont bf_symbol = BaseFont.createFont(BaseFont.SYMBOL, "Cp1252", false);

            // headers and footers must be added before the document is opened
            HeaderFooter footer = new HeaderFooter(
                    new Phrase("This is page: ", new Font(bf_courier)), true);
            footer.setBorder(Rectangle.NO_BORDER);
            footer.setAlignment(Element.ALIGN_CENTER);
            document.setFooter(footer);

            HeaderFooter header = new HeaderFooter(
                    new Phrase("STK Pardubice 3, Technická 131, 583 01 ", new Font(bf_times)), false);
            header.setAlignment(Element.ALIGN_CENTER);
            document.setHeader(header);

            document.open();

            int y_line1 = 640;
            int y_line2 = y_line1 - 50;
            int y_line3 = y_line2 - 170;
            int y_line4 = y_line3 - 200;

            // draw a few lines ...
            PdfContentByte cb = writer.getDirectContent();
            cb.moveTo(50, y_line2);
            cb.lineTo(550, y_line2);
            cb.moveTo(50, y_line3);
            cb.lineTo(550, y_line3);
            cb.moveTo(50, y_line4);
            cb.lineTo(550, y_line4);
            cb.stroke();
            // ... and some text that is aligned in various ways
            cb.beginText();
            cb.setFontAndSize(bf_helv, 12);
            String text = "Sample text for alignment";

            cb.endText();
            String title="STK Pardubice";
            Font tittleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD,35);
            Paragraph titlePara = new Paragraph(title,tittleFont);
            titlePara.setAlignment(Element.ALIGN_RIGHT);
            document.add(titlePara);

            // add an image, scale it down by half, and put at an absolute position
            try {
                Image simple = Image.getInstance("images/stk.png");
                simple.setAbsolutePosition(50, 680);
                simple.scalePercent(13);
                document.add(simple);
            } catch (Exception ex) {
                System.out.println("no image for you");
            }

            Font tittleFontInspection = FontFactory.getFont(FontFactory.HELVETICA_BOLD,20);
            Paragraph inspectionPara = new Paragraph("Protokol o technické kontrole",tittleFontInspection);
            inspectionPara.setLeading(0.0f,6.0f);
            inspectionPara.setAlignment(Element.ALIGN_CENTER);
            document.add(inspectionPara);


            Font carTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,13);

            Paragraph cartittlePara = new Paragraph("Popis auta",carTitle);
            cartittlePara.setLeading(30.0f,0.0f);
            cartittlePara.setAlignment(Element.ALIGN_LEFT);
            document.add(cartittlePara);

            Table table1 = new Table(2);
            table1.setBorderWidth(0);
            table1.setPadding(0);
            table1.setSpacing(5);

            Cell vinCell=new Cell("VIN:");
            vinCell.setBorderWidth(0);
            Cell spzCell=new Cell("SPZ:");
            spzCell.setBorderWidth(0);
            Cell ownerCell=new Cell("Majitel:");
            ownerCell.setBorderWidth(0);
            Cell dateCell=new Cell("Datum kontroly:");
            dateCell.setBorderWidth(0);
            Cell vinCellData=new Cell(car.getVin());
            vinCellData.setBorderWidth(0);
            Cell spzCellData=new Cell(car.getSpz());
            spzCellData.setBorderWidth(0);
            Cell ownerCellData=new Cell(name);
            ownerCellData.setBorderWidth(0);
            Cell dateCellData=new Cell(inspection.getDate().toString());
            dateCellData.setBorderWidth(0);

            table1.addCell(vinCell);
            table1.addCell(vinCellData);
            table1.addCell(spzCell);
            table1.addCell(spzCellData);
            table1.addCell(ownerCell);
            table1.addCell(ownerCellData);
            table1.addCell(dateCell);
            table1.addCell(dateCellData);
            document.add(table1);




            Font carFaultTittle = FontFactory.getFont(FontFactory.HELVETICA_BOLD,13);
            Paragraph carFaultTittlePara = new Paragraph("Závady na vozidle",carFaultTittle);
            carFaultTittlePara.setLeading(0.0f,2.0f);
            carFaultTittlePara.setAlignment(Element.ALIGN_LEFT);
            document.add(carFaultTittlePara);


            Table table2 = new Table(2);
            table2.setBorderWidth(0);
            table2.setPadding(0);
            table2.setSpacing(5);

            Cell aCell=new Cell("LEHKÉ (A) (pocet závad) :");
            aCell.setBorderWidth(0);
            Cell bCell=new Cell("VÁŽNÉ (B) (pocet závad) :");
            bCell.setBorderWidth(0);
            Cell cCell=new Cell("NEBEZPEČNÉ (C) (pocet závad) :");
            cCell.setBorderWidth(0);

            Cell aCellData=new Cell(String.valueOf(a));
            aCellData.setBorderWidth(0);
            Cell bCellData=new Cell(String.valueOf(b));
            bCellData.setBorderWidth(0);
            Cell cCellData=new Cell(String.valueOf(c));
            cCellData.setBorderWidth(0);

            table2.addCell(aCell);
            table2.addCell(aCellData);
            table2.addCell(bCell);
            table2.addCell(bCellData);
            table2.addCell(cCell);
            table2.addCell(cCellData);

            document.add(table2);



            // we're done!
            document.close();


        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
