package com.ownerofglory.model.mapper;

import com.ownerofglory.model.User;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.dto.system.RegisterUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userDTOToUser(UserDTO dto);
    UserDTO userToUserDTO(User user);
    User registerUserDTOToUser(RegisterUserDTO registerUser);
}
