package org.app.reservation.web_service;

import org.app.reservation.dto.CreateReservationRequest;
import org.app.reservation.dto.ReservationDto;
import org.app.response.Response;

import javax.activation.DataHandler;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;
import java.util.UUID;

@MTOM
@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface ReservationWebService {
    @WebMethod
    DataHandler create(CreateReservationRequest reservationRequest);

    @WebMethod
    Response<String> resignFromReservation(UUID reservationId);
}
