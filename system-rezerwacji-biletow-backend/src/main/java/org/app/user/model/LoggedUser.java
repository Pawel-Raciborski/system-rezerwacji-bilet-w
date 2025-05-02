package org.app.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="loggedUser")
@XmlAccessorType(XmlAccessType.FIELD)
public class LoggedUser implements Serializable {
    @XmlElement
    private Long userId;
    @XmlElement
    private String name;
    @XmlElement
    private String phoneNumber;
    @XmlElement
    private String email;
    @XmlElement
    private boolean isLogged;
}
