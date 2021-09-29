package br.edu.fasam.clean.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private Long id;
    private String lat;
    private String lng;
}
