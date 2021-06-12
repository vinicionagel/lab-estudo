package br.com.labestudo.api.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.entity.SelfRegisterUser;
import br.com.labestudo.api.model.mapper.SelfRegisterUserMapper;
import br.com.labestudo.api.repository.SelfRegisterRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SelfRegisterService extends SelfRegisterable {

	private final SelfRegisterRepository selfRegisterRepository;

	private final PassFlowVerifyService passFlowVerifyService;

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	private final SelfRegisterUserMapper selfRegisterUserMapper;

	@Override
	protected void verifyIfRegistered(String email) throws UserRegisteredException {
		if (userRepository.findByEmail(email).isPresent()) {
			throw new UserRegisteredException();
		}
	}

	@Override
	protected void verifyPass(String pass) throws PassValidationException {
		passFlowVerifyService.verifyPass(pass);
	}

	@Override
	protected void encryptPass(UserDto userDto) {
		userDto.setPass(passwordEncoder.encode(userDto.getPass()));
	}

	@Override
	protected SelfRegisterUser create(UserDto userDto) {
		SelfRegisterUser newRegister = selfRegisterUserMapper.toSelfRegisterUser(userDto);
		Optional<SelfRegisterUser> optOldRegister = selfRegisterRepository.findByEmail(newRegister.getEmail());
		if (optOldRegister.isPresent()) {
			SelfRegisterUser oldRegister = optOldRegister.get();
			oldRegister.setName(newRegister.getName());
			oldRegister.setPass(newRegister.getPass());
			return selfRegisterRepository.save(oldRegister);
		} else {
			newRegister.setId(UUID.randomUUID().toString());
			return selfRegisterRepository.save(newRegister);
		}
	}

	@Override
	protected void sendValidation(SelfRegisterUser selfRegisterUser) {
		
	}
}
