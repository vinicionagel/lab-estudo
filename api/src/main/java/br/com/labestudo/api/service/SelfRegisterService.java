package br.com.labestudo.api.service;

import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.exception.SelfRegisterFailedValidationException;
import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.EmailDto;
import br.com.labestudo.api.model.dto.HashDto;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.entity.Parameter;
import br.com.labestudo.api.model.entity.SelfRegisterUser;
import br.com.labestudo.api.model.mapper.SelfRegisterUserMapper;
import br.com.labestudo.api.model.mapper.UserMapper;
import br.com.labestudo.api.repository.ParameterRepository;
import br.com.labestudo.api.repository.SelfRegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class SelfRegisterService extends SelfRegisterable {

	private final SelfRegisterRepository selfRegisterRepository;

	private final PassFlowVerifyService passFlowVerifyService;

	private final PasswordEncoder passwordEncoder;

	private final UserRepository userRepository;

	private final ParameterRepository parameterRepository;

	private final SelfRegisterUserMapper selfRegisterUserMapper;

	private final EmailService emailService;

	private final MessageSource messageSource;

	private final UserMapper userMapper;

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
		var newRegister = selfRegisterUserMapper.toSelfRegisterUser(userDto);
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
		emailService.send(EmailDto.builder()
				.template("self-register-email.html")
				.recipients(Set.of(selfRegisterUser.getEmail()))
				.subject(messageSource.getMessage("email.self.confirmAccount", null, LocaleContextHolder.getLocale()))
				.values(Map.of("name", selfRegisterUser.getName(), "link", "", "uuid", selfRegisterUser.getId()))
				.build());
	}

	public void validateAccount(HashDto hash) throws SelfRegisterFailedValidationException {
		Optional<SelfRegisterUser> selfRegister = selfRegisterRepository.findById(hash.getHash());
		if (selfRegister.isPresent()) {
			var accountConfirmationPeriod = parameterRepository.findById("accountConfirmationPeriod").map(Parameter::getValue).map(Integer::valueOf).orElse(12);
			var selfRegisterUser = selfRegister.get();
			var date = Optional.ofNullable(selfRegisterUser.getUpdated()).orElse(selfRegister.get().getCreated());
			if (date.plusHours(accountConfirmationPeriod).isAfter(OffsetDateTime.now())) {
				userRepository.save(userMapper.toUser(selfRegisterUser));
			}
			selfRegisterRepository.delete(selfRegisterUser);
		} else {
			throw new SelfRegisterFailedValidationException();
		}
	}
}
