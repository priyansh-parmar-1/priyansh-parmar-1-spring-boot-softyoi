package com.example.security.mapper;

import com.example.security.dto.UserDto;
import com.example.security.model.Role;
import com.example.security.model.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-30T11:10:50+0530",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.10 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        String name = null;
        String add = null;
        Role role = null;

        User user = new User( name, add, role );

        return user;
    }
}
