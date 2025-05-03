package org.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="director")
@XmlAccessorType(XmlAccessType.FIELD)
public class DirectorDto {
    @XmlElement
    private Long id;
    @XmlElement
    private String name;
    @XmlElement
    private String surname;
    @XmlElement
    private String email;
    @XmlElement
    private String phone;
}
