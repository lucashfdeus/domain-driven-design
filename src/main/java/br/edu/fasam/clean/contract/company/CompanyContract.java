package br.edu.fasam.clean.contract.company;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CompanyContract {
    protected String name;
    protected String catchPhrase;
    protected String bs;
}
