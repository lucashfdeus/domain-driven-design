package br.edu.fasam.clean.mappers;

import br.edu.fasam.clean.contract.user.UserContract;
import br.edu.fasam.clean.contract.user.UserRequest;
import br.edu.fasam.clean.contract.user.UserResponse;
import br.edu.fasam.clean.model.User;
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
public interface UserMapper {

    UserMapper INSTANCE = getMapper(UserMapper.class);

    @IterableMapping(elementTargetType = UserContract.class)
    User toModel(UserRequest userRequest);

    UserRequest toRequest(User user);

    @IterableMapping(elementTargetType = UserContract.class)
    UserResponse toResponse(User user);
}
