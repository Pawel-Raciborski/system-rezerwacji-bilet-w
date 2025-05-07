package org.app.user.mapper;

import org.app.user.model.LoggedUser;
import org.app.user.model.User;

public interface UserMapper {
    static LoggedUser mapToLoggedUser(User user) {
        return LoggedUser.builder()
                .userId(user.getUserId())
                .name(user.getName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .isLogged(true)
                .build();
    }
}
