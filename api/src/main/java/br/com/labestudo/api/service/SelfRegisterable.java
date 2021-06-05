package br.com.labestudo.api.service;

import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.entity.SelfRegisterUser;

public abstract class SelfRegisterable {

    public void selfRegister(UserDto userDto) throws UserRegisteredException {
        verifyIfRegistered(userDto.getEmail());
        verifyPass(userDto.getPass());
        encryptPass(userDto);
        SelfRegisterUser selfRegisterUser = create(userDto);
        sendValidation(selfRegisterUser);
    }

    protected abstract void verifyIfRegistered(String email) throws UserRegisteredException;
    protected abstract void verifyPass(String pass);
    protected abstract void encryptPass(UserDto userDto);
    protected abstract SelfRegisterUser create(UserDto userDto);
    protected abstract void sendValidation(SelfRegisterUser selfRegisterUser);
}
