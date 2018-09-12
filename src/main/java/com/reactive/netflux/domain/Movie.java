package com.reactive.netflux.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.NonFinal;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor

public class Movie {

    private String id;

    @NonFinal
    private String title;

}
