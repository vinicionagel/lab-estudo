package br.com.labestudo.api.service;

import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.entity.SelfRegisterUser;
import br.com.labestudo.api.repository.SelfRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SelfRegisterService extends SelfRegisterable {

    private SelfRegisterRepository selfRegisterRepository;

    private UserRepository userRepository;

    @Override
    protected void verifyIfRegistered(String email) throws UserRegisteredException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserRegisteredException();
        }
    }

    @Override
    protected void verifyPass(String pass) {
        boolean isCorrectLength = pass.length() >= 6 && pass.length() <= 16;
        boolean containsNumber = false;
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;

        for (int i = 0; i < pass.length(); i++) {
            if (Character.isDigit(pass.charAt(i))) {
                containsNumber = true;
            } else if (Character.isUpperCase(pass.charAt(i))) {
                containsUpperCase = true;
            } else if (Character.isLowerCase(pass.charAt(i))) {
                containsLowerCase = true;
            }
        }
    }

    @Override
    protected void encryptPass(UserDto userDto) {

    }

    @Override
    protected SelfRegisterUser create(UserDto userDto) {
        return null;
    }

    @Override
    protected void sendValidation(SelfRegisterUser selfRegisterUser) {

    }
}
