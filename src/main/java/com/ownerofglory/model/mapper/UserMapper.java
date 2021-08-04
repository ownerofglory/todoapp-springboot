package com.ownerofglory.model.mapper;

import com.ownerofglory.model.User;
import com.ownerofglory.model.auth.UserDetails;
import com.ownerofglory.model.dto.UserDTO;
import com.ownerofglory.model.dto.system.RegisterUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User userDTOToUser(UserDTO dto);
    UserDTO userToUserDTO(User user);
    @Mapping(source = "authority", target = "authorities", qualifiedByName = "convertAuthorities")
    UserDetails userToUserDetails(User user);
    User registerUserDTOToUser(RegisterUserDTO registerUser);
    UserDTO registerUserDTOToUserDTO(RegisterUserDTO registerUser);


    @Named("convertAuthorities")
    default Set<? extends GrantedAuthority> convertAuthorities(String authority) {
        SimpleGrantedAuthority simpleAuthority = new SimpleGrantedAuthority(authority);
        return Set.of(simpleAuthority);
    }
}
