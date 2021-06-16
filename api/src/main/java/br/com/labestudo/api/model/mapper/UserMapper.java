package br.com.labestudo.api.model.mapper;

import org.mapstruct.Mapper;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.model.dto.UserDto;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel="spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	User toUser(UserDto userDto);
}
