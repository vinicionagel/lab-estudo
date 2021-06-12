package br.com.labestudo.api.model.mapper;

import org.mapstruct.Mapper;

import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.entity.SelfRegisterUser;

@Mapper(componentModel="spring")
public interface SelfRegisterUserMapper {

	SelfRegisterUser toSelfRegisterUser(UserDto userDrto);
}
