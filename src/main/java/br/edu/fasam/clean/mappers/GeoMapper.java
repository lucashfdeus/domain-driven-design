package br.edu.fasam.clean.mappers;

import br.edu.fasam.clean.contract.geo.GeoContract;
import br.edu.fasam.clean.contract.geo.GeoRequest;
import br.edu.fasam.clean.contract.geo.GeoResponse;
import br.edu.fasam.clean.model.Geo;
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
public interface GeoMapper {

    GeoMapper INSTANCE = getMapper(GeoMapper.class);

    @IterableMapping(elementTargetType = GeoContract.class)
    Geo toModel(GeoRequest companyRequest);

    GeoRequest toRequest(Geo geo);

    @IterableMapping(elementTargetType = GeoContract.class)
    GeoResponse toResponse(Geo geo);

}
