package frontend.manager;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import frontend.App;
import javafx.event.ActionEvent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfManager {

    public void createPDF() throws FileNotFoundException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("iTextHelloWorld.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.add(new Paragraph("Používatel: " + App.getActivUser().getUserName(), font));

        document.add(new Paragraph("Kolekcia: " + App.getCollection().getName(), font));
        document.add(new Paragraph("\t Vytvorená: " + App.getCollection().getCreationDate(), font));

        document.add(new Paragraph("Názov: " + App.getActItem().getName(), font));
        document.add(new Paragraph("Autor: " + App.getActItem().getAuthor(), font));
        document.add(new Paragraph("Značka: " + App.getActItem().getBrand(), font));
        document.add(new Paragraph("Rok výroby: " + App.getActItem().getProductionYear(), font));
        document.add(new Paragraph("Krajina pôvodu: " + App.getActItem().getOriginCountry(), font));
        document.add(new Paragraph("Žáner: " + App.getActItem().getGenre(), font));
        document.add(new Paragraph("Rozmery: " + App.getActItem().getDimensions(), font));
        document.add(new Paragraph("Nákupná cena: " + App.getActItem().getPrice(), font));
        document.add(new Paragraph("Predpokladaná hodnota: " + App.getActItem().getValue(), font));
        document.add(new Paragraph("Miesto nadobudnutia: " + App.getActItem().getAcquirementLocation(), font));
        document.add(new Paragraph("Dátum nadobudnutia: " + App.getActItem().getAcquirementDate(), font));
        document.add(new Paragraph("Popis: " + App.getActItem().getNote(), font));

        document.close();
    }
}
