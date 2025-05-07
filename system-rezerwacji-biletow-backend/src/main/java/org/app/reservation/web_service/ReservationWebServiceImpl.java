package org.app.reservation.web_service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.app.pdf.PDFGenerator;
import org.app.reservation.dto.CreateReservationRequest;
import org.app.reservation.dto.ReservationDto;
import org.app.reservation.mapper.ReservationMapper;
import org.app.reservation.service.ReservationService;
import org.app.response.Response;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.jws.WebService;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

@WebService(endpointInterface = "org.app.reservation.web_service.ReservationWebService")
public class ReservationWebServiceImpl implements ReservationWebService {
    private final ReservationService reservationService;

    public ReservationWebServiceImpl() {
        this.reservationService = new ReservationService();
    }

    @Override
    public DataHandler create(CreateReservationRequest reservationRequest) {
        ReservationDto reservationDto = ReservationMapper.mapToDto(reservationService.create(reservationRequest));
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PDFGenerator.generateDocument(reservationDto, baos);

            byte[] pdfData = baos.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(pdfData,"application/pdf");
            return new DataHandler(dataSource);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response<String> resignFromReservation(UUID reservationId) {
        reservationService.removeReservation(reservationId);

        return Response.<String>builder()
                .responseMessage("Pomyślnie usunięto rezerwację")
                .build();
    }
}
