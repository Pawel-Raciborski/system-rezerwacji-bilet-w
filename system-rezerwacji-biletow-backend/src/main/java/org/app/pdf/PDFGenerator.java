package org.app.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.app.reservation.dto.ReservationDto;
import org.app.room.dto.SeatDto;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class PDFGenerator {

    public static void generateDocument(ReservationDto reservationDto, OutputStream baos){
        try(PDDocument document = new PDDocument()){
            PDPage page = new PDPage();
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document,page);
            float margin = 50;
            float yPosition = page.getMediaBox().getHeight() - margin;
            float pageWidth = page.getMediaBox().getWidth();
            PDFont boldFont = PDType1Font.TIMES_ROMAN;
            PDFont regularFont = PDType1Font.TIMES_ROMAN;
            PDFont smallFont = PDType1Font.TIMES_ROMAN;
            PDColor grayColor = new PDColor(new float[]{0.5f, 0.5f, 0.5f}, PDDeviceRGB.INSTANCE);
            PDColor backgroundColor = new PDColor(new float[]{0.95f, 0.95f, 0.95f}, PDDeviceRGB.INSTANCE);

            String title = "Podsumowanie rezerwacji";
            float titleWidth = boldFont.getStringWidth(title) / 1000 * 16;
            float titleXPosition = (pageWidth - titleWidth) / 2;
            contentStream.setFont(boldFont, 16);
            contentStream.setNonStrokingColor(0, 0, 0); // Czarny kolor tekstu
            contentStream.beginText();
            contentStream.newLineAtOffset(titleXPosition, yPosition);
            contentStream.showText(title);
            contentStream.endText();
            yPosition -= 25; // Odstęp

            float personalDataStartY = yPosition;
            float personalDataHeight = 15 * 3 + 5; // Wysokość dla 3 linii tekstu + trochę odstępu
            contentStream.setNonStrokingColor(backgroundColor);
            contentStream.addRect(margin, yPosition - personalDataHeight, pageWidth - 2 * margin, personalDataHeight);
            contentStream.fill();
            contentStream.setNonStrokingColor(0, 0, 0); // Resetuj kolor tekstu

            contentStream.setFont(regularFont, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition - 12);
            contentStream.showText("Imie i nazwisko: " + reservationDto.getUser().getName());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Numer telefonu: " + reservationDto.getUser().getPhoneNumber());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Email: " + reservationDto.getUser().getEmail());
            contentStream.endText();
            yPosition -= personalDataHeight + 20;

            contentStream.setFont(boldFont, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin, yPosition - 12);
            contentStream.showText("Film \"" + reservationDto.getSpectacle().getMovie().getTitle()+ "\"");
            contentStream.endText();

            contentStream.setFont(regularFont, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Dlugosc filmu: " + reservationDto.getSpectacle().getMovie().getLength());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Data seansu: " + reservationDto.getSpectacle().getDate());
            contentStream.newLineAtOffset(0, -15);
            contentStream.showText("Sala: " + reservationDto.getSpectacle().getRoom().getRoomNumber());
            contentStream.endText();
            yPosition -= (15 * 4) + 20; // Odstęp po sekcji filmu

            float tableWidth = pageWidth - 2 * margin;
            float colWidth = tableWidth / 2;
            float tableStartY = yPosition;
            float cellHeight = 20;
            float textMargin = 5;

            // Nagłówki tabeli
            contentStream.setFont(boldFont, 12);
            contentStream.setNonStrokingColor(0, 0, 0);
            contentStream.beginText();
            contentStream.newLineAtOffset(margin + textMargin, yPosition - textMargin - (cellHeight - 12) / 2);
            contentStream.showText("Numer miejsca");
            contentStream.newLineAtOffset(colWidth, 0);
            contentStream.showText("Rzad");
            contentStream.endText();
            yPosition -= cellHeight;

            contentStream.setFont(regularFont, 12);
            contentStream.setNonStrokingColor(0, 0, 0);

            for (SeatDto seat : reservationDto.getReservedSeats()) {
                contentStream.beginText();
                contentStream.newLineAtOffset(margin + textMargin, yPosition - textMargin - (cellHeight - 12) / 2);
                contentStream.showText(seat.getRow());
                contentStream.newLineAtOffset(colWidth, 0);
                contentStream.showText(seat.getPlaceNumber().toString());
                contentStream.endText();
                yPosition -= cellHeight;
            }

            LocalDate now = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String footerText = "Wygenerowano: " + formatter.format(now);
            contentStream.setFont(smallFont, 10);
            contentStream.setNonStrokingColor(grayColor);
            float footerWidth = smallFont.getStringWidth(footerText) / 1000 * 10;
            float footerXPosition = (pageWidth - footerWidth) / 2;
            float footerYPosition = margin;
            contentStream.beginText();
            contentStream.newLineAtOffset(footerXPosition, footerYPosition);
            contentStream.showText(footerText);
            contentStream.endText();

            contentStream.close();
            document.save(baos);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
