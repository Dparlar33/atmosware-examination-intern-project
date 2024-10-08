package com.atmosware.managementService.core.utilities.mapping;

import com.atmosware.managementService.business.dtos.requests.user.RegisterRequest;
import com.atmosware.managementService.business.dtos.requests.user.UpdateUserRequest;
import com.atmosware.managementService.business.dtos.responses.user.GetUserByIdResponse;
import com.atmosware.managementService.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User registerRequestToUser(RegisterRequest registerRequest);

    User updateUserRequestToUser(UpdateUserRequest updateUserRequest);

    @Mapping(source = "email", target = "email")
    GetUserByIdResponse userToGetUserById(User user);
}
