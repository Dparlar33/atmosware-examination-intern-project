package com.atmosware.managementService.core.utilities.mapping;

import com.atmosware.managementService.business.dtos.GetAllUsersResponse;
import com.atmosware.managementService.business.dtos.GetUserByIdResponse;
import com.atmosware.managementService.business.dtos.RegisterRequest;
import com.atmosware.managementService.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "email", target = "email")
    User registerRequestToUser(RegisterRequest registerRequest);

    GetUserByIdResponse userToGetUserById(User user);
}
