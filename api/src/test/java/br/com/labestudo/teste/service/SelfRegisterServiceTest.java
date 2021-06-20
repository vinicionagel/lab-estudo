package br.com.labestudo.teste.service;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.exception.UserRegisteredException;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.mapper.UserMapper;
import br.com.labestudo.api.service.SelfRegisterService;
import br.com.labestudo.teste.fixture.SelfRegisterFixture;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SelfRegisterServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserMapper userMapper = UserMapper.INSTANCE;

    @InjectMocks
    private SelfRegisterService selfRegisterService;

    @Test
    void whenAlreadyRegisteredUserInformedThenAnExceptionShouldBeThrown() {
        UserDto userDto = SelfRegisterFixture.validUserDto();
        User duplicatedUser = userMapper.toUser(userDto);
        duplicatedUser.setId(1L);

        when(userRepository.findByEmail(userDto.getEmail())).thenReturn(Optional.of(duplicatedUser));

        assertThrows(UserRegisteredException.class, () -> selfRegisterService.selfRegister(userDto));
    }

}
