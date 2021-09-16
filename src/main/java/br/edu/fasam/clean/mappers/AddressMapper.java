package br.edu.fasam.clean.mappers;

import br.edu.fasam.clean.contract.address.AddressContract;
import br.edu.fasam.clean.contract.address.AddressRequest;
import br.edu.fasam.clean.contract.address.AddressResponse;
import br.edu.fasam.clean.model.Address;
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
public interface AddressMapper {

    AddressMapper INSTANCE = getMapper(AddressMapper.class);

    @IterableMapping(elementTargetType = AddressContract.class)
    Address toModel(AddressRequest addressRequest);

    AddressRequest toRequest(Address address);

    @IterableMapping(elementTargetType = AddressContract.class)
    AddressResponse toResponse(Address address);
}
