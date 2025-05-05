package org.app.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.app.movie.Director;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name="movie")
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieDto {
    @XmlElement
    private UUID movieId;

    @XmlElement
    private String title;

    @XmlElement
    private String description;

    @XmlElement
    private String length;

    @XmlElement
    private byte[] posterData;

    @XmlElement
    private BigDecimal stars;

    @XmlElement(name = "director")
    private DirectorDto director;
}
