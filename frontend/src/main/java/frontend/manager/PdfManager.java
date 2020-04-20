package frontend.manager;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import frontend.App;
import frontend.model.Collection;
import frontend.model.Item;
import frontend.model.User;
import javafx.event.ActionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ResourceBundle;

public class PdfManager {

    Logger logger = LoggerFactory.getLogger(PdfManager.class);

    public void createPDF() {
        Document document = new Document();
        User user = App.getActivUser();
        Item item = App.getActItem();
        Collection collection = App.getCollection();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());

        try {
            String name = item.getName().replaceAll("\\s", "");
            PdfWriter.getInstance(document, new FileOutputStream(name + ".pdf"));

            document.open();
            Font title = FontFactory.getFont(FontFactory.COURIER, 20, BaseColor.BLACK);
            Font font = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);

            document.add(new Chunk(item.getName(), title));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(resourceBundle.getString("PDFController.user") + ": " + user.getUserName(), font));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(resourceBundle.getString("PDFController.collection") + ": " + collection.getName(), font));
            document.add(new Paragraph(resourceBundle.getString("PDFController.created") + ": " + collection.getCreationDate(), font));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createNameL") + ": " + item.getName(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createAuthorL") + ": " + item.getAuthor(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createBrandL") + ": " + item.getBrand(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createYearL") + ": " + item.getProductionYear(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createCountryL") + ": " + item.getOriginCountry(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createGenreL") + ": " + item.getGenre(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createDimensionsL") + ": " + item.getDimensions(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createPriceL") + ": " + item.getPrice(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createValueL") + ": " + item.getValue(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createAqPlaceL") + ": " + item.getAcquirementLocation(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createDateL") + ": " + item.getAcquirementDate(), font));
            document.add(new Paragraph(resourceBundle.getString("ItemController.createNoteL") + ": " + item.getNote(), font));

            document.close();

            logger.info("Successfuly created PDF: " + name);

        } catch (Exception e){
            logger.error("Cannot create PDF");
        }
    }
}
