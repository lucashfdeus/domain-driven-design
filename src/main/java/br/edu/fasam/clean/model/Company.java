package br.edu.fasam.clean.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Company {
    private String lat;
    private String lng;
}
