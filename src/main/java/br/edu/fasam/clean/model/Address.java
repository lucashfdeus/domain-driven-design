package br.edu.fasam.clean.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {
    private Long id;
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;
}
