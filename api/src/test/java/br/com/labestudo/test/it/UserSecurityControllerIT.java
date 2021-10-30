package br.com.labestudo.test.it;

import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.model.dto.UserDto;
import br.com.labestudo.api.model.mapper.UserMapper;
import br.com.labestudo.teste.fixture.repository.UserRepositoryFixture;
import br.com.labestudo.teste.util.ApiApplicationIT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static br.com.labestudo.teste.util.JsonConvertionUtils.asJsonString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserSecurityControllerIT extends ApiApplicationIT {

    private MockMvc mockMvc;

    @Autowired
    private UserRepositoryFixture userRepositoryFixture;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();
    }

    @BeforeEach
    public void initTest() {
        userRepositoryFixture.deleteAll();
    }

    @Test
    @WithMockUser(username = "validUser@gmail.com", password = "pwd", roles = "USER")
    void whenPUTIsCalled_WithValidLoggedUser_ThenUserWasUpdated() throws Exception {
        // given
        var user = userRepositoryFixture.get();
        UserDto userDto = userMapper.toUserDto(user);
        userDto.setName("batata");
        // then
        mockMvc.perform(put("/user/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isNoContent());

        Assertions.assertThat(userRepository.findById(user.getId()).orElseThrow().getName()).isEqualTo(userDto.getName());
    }

    @Test
    @WithMockUser(username = "invalidUser@gmail.com", password = "pwd", roles = "USER")
    void whenPUTIsCalled_WithInvalidLoggedUser_Then() throws Exception {
        // given
        var user = userRepositoryFixture.get();
        UserDto userDto = userMapper.toUserDto(user);
        userDto.setName("batata");
        // then
        mockMvc.perform(put("/user/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isForbidden());
    }
}