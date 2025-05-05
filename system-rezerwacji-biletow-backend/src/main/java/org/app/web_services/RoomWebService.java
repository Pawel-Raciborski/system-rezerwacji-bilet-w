package org.app.web_services;

import org.app.room.dto.RoomDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT)
public interface RoomWebService {
    @WebMethod
    RoomDto create(RoomDto roomDto);
}
