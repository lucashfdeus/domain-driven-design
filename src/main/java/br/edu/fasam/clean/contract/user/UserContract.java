package br.edu.fasam.clean.contract.user;

import br.edu.fasam.clean.contract.address.AddressContract;
import br.edu.fasam.clean.contract.company.CompanyContract;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class UserContract {
    protected int id;
    protected String name;
    protected String username;
    protected String email;
    protected AddressContract addressContract;
    protected String phone;
    protected String website;
    protected CompanyContract companyContract;
}
