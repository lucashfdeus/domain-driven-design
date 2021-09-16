package br.edu.fasam.clean.contract.address;

import br.edu.fasam.clean.contract.geo.GeoContract;
import br.edu.fasam.clean.model.Geo;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class AddressContract {
    protected String street;
    protected String suite;
    protected String city;
    protected String zipcode;
    protected GeoContract geoContract;
}
