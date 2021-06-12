package br.com.labestudo.api.model.mapper;

import org.mapstruct.Mapper;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.model.dto.UserDto;

@Mapper(componentModel="spring")
public interface UserMapper {

	User toUser(UserDto userDto);
}
