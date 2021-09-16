package br.edu.fasam.clean.mappers;

import br.edu.fasam.clean.contract.company.CompanyContract;
import br.edu.fasam.clean.contract.company.CompanyRequest;
import br.edu.fasam.clean.contract.company.CompanyResponse;
import br.edu.fasam.clean.model.Company;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * Documentação referente a construção da Interface utlizando MapperStruct
 * {@link https://mapstruct.org/}
 */
@Mapper(componentModel = "spring",
        imports = {ArrayList.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyMapper {

    CompanyMapper INSTANCE = getMapper(CompanyMapper.class);

    @IterableMapping(elementTargetType = CompanyContract.class)
    Company toModel(CompanyRequest companyRequest);

    CompanyRequest toRequest(Company company);

    @IterableMapping(elementTargetType = CompanyContract.class)
    CompanyResponse toResponse(Company company);

}
