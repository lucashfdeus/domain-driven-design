package br.edu.fasam.clean.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Geo {
    private Long id;
    private String lat;
    private String lng;
}
