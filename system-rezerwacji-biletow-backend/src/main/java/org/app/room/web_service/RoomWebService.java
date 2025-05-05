package org.app.room.web_service;

import org.app.response.Response;
import org.app.room.dto.RoomDetails;
import org.app.room.dto.RoomDto;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.util.UUID;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface RoomWebService {
    @WebMethod
    @WebResult(name = "createdRoom")
    Response<RoomDto> create(RoomDto roomDto);

    @WebMethod
    @WebResult(name = "roomDetails")
    RoomDetails getRoomDetails(@WebParam(name = "roomId") UUID roomId);
}
