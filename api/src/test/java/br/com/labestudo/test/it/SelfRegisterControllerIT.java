package br.com.labestudo.test.it;

import br.com.labestudo.api.auth.model.entity.User;
import br.com.labestudo.api.auth.repository.UserRepository;
import br.com.labestudo.api.controller.SelfRegisterController;
import br.com.labestudo.api.exception.ApiExceptionHandler;
import br.com.labestudo.api.exception.PathConfigurableException;
import br.com.labestudo.api.model.dto.HashDto;
import br.com.labestudo.api.model.entity.SelfRegisterUser;
import br.com.labestudo.api.repository.SelfRegisterRepository;
import br.com.labestudo.api.service.file.FileService;
import br.com.labestudo.teste.fixture.SelfRegisterFixture;
import br.com.labestudo.teste.fixture.repository.SelfRegisterRepositoryFixture;
import br.com.labestudo.teste.util.ApiApplicationIT;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.List;

import static br.com.labestudo.teste.util.JsonConvertionUtils.asJsonString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SelfRegisterControllerIT extends ApiApplicationIT {

    @Autowired
    private SelfRegisterRepositoryFixture selfRegisterRepositoryFixture;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SelfRegisterController selfRegisterController;

    @Autowired
    private SelfRegisterRepository selfRegisterRepository;

    @MockBean
    private FileService fileService;

    private static final String SELF_REGISTER_API_URL_PATH = "/selfregister";

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(selfRegisterController)
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .setControllerAdvice(new ApiExceptionHandler(messageSource))
                .build();
    }

    @BeforeEach
    public void initTest() {
        userRepository.deleteAll();
        selfRegisterRepositoryFixture.deleteAll();
    }

    @Test
    void whenPUTIsCalledThenASelfRegisterUserIsCreated() throws Exception {
        // given
        var userDto = SelfRegisterFixture.validUserDto();
        // then
        mockMvc.perform(put(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isCreated());

        List<SelfRegisterUser> all = selfRegisterRepository.findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void whenPUTIsCalledThenAWithASelfRegisterUserIsUpdate() throws Exception {
        var selfRegistredUser = selfRegisterRepositoryFixture.get();
        // given
        var userDto = SelfRegisterFixture.validUserDto();
        // then
        mockMvc.perform(put(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isCreated());

        List<SelfRegisterUser> all = selfRegisterRepository.findAll();
        Assertions.assertThat(all).isNotEmpty();
    }

    @Test
    void whenPutIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
        // given
        var userDto = SelfRegisterFixture.emptyUserDto();
        // then
        mockMvc.perform(put(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPutIsCalledWithWrongEmailThenAnErrorIsReturned() throws Exception {
        // given
        var userDto = SelfRegisterFixture.invalidEmailUserDto();
        // then
        mockMvc.perform(put(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(userDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostIsCalledWithWrongHashThenAnErrorIsReturned() throws Exception {
        // given
        var hashDto = new HashDto("invalidHash");
        // then
        mockMvc.perform(post(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(hashDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenPostIsCalledWithValidHashThenAnOkIsReturned() throws Exception {
        var selfRegistredUser = selfRegisterRepositoryFixture.get();
        // given
        var hashDto = new HashDto(selfRegistredUser.getId());
        // then
        mockMvc.perform(post(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(hashDto)))
                .andExpect(status().isOk());
    }

    @Test
    void whenPostIsCalledWithValidHashButWasInvalidPathFileThenNoCreateUser() throws Exception {
        var selfRegistredUser = selfRegisterRepositoryFixture.get();
        // given
        var hashDto = new HashDto(selfRegistredUser.getId());
        doThrow(new PathConfigurableException()).when(fileService).configurePath(anyLong());
        // then
        mockMvc.perform(post(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(hashDto)))
                .andExpect(status().isBadRequest());

        List<User> all = userRepository.findAll();
        Assertions.assertThat(all).isEmpty();
    }

    @Test
    void whenPostIsCalledWithEmptyHashThenAnErrorIsReturned() throws Exception {
        // given
        var hashDto = new HashDto("");
        // then
        mockMvc.perform(post(SELF_REGISTER_API_URL_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(hashDto)))
                .andExpect(status().isBadRequest());
    }

}
