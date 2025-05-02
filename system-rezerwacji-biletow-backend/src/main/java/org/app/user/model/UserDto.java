package org.app.user.model;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String password;
    private String phoneNumber;
    private String email;

}
