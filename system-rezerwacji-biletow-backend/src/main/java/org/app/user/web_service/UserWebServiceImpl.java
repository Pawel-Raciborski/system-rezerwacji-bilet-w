package org.app.user.web_service;

import lombok.AllArgsConstructor;
import org.app.user.model.Credentials;
import org.app.user.model.LoggedUser;
import org.app.user.model.UserDto;
import org.app.user.repository.UserRepositoryImpl;
import org.app.user.services.UserService;

import javax.jws.WebService;

@WebService(endpointInterface = "org.app.user.web_service.UserWebService")
@AllArgsConstructor
public class UserWebServiceImpl implements UserWebService {
    private final UserService userService;

    public UserWebServiceImpl() {
        this.userService = new UserService(new UserRepositoryImpl());
    }

    @Override
    public void saveUser(UserDto user) {
        userService.register(user);
    }

    @Override
    public LoggedUser login(Credentials credentials) {
        return userService.login(credentials);
    }
}
