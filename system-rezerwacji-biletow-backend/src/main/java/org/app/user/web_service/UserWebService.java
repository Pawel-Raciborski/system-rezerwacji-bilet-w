package org.app.user.web_service;

import org.app.user.model.Credentials;
import org.app.user.model.LoggedUser;
import org.app.user.model.User;
import org.app.user.model.UserDto;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface UserWebService {

    @WebMethod
    void saveUser(UserDto user);

    @WebMethod
    @WebResult(name = "loggedUser")
    LoggedUser login(Credentials credentials);
}
