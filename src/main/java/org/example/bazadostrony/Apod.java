package org.example.bazadostrony;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Apod {
    private String date;
    private String explanation;
    private String url;
    private String hdurl;
    private String title;
    private String media_type;
}
