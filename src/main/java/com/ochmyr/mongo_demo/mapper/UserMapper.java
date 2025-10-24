package com.ochmyr.mongo_demo.mapper;

import com.ochmyr.mongo_demo.dto.UserRequestDTO;
import com.ochmyr.mongo_demo.dto.UserResponseDTO;
import com.ochmyr.mongo_demo.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO dto);
    UserResponseDTO toResponse(User entity);

}
