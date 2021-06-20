package br.com.labestudo.teste.controller;

import br.com.labestudo.api.controller.SelfRegisterController;
import br.com.labestudo.api.service.SelfRegisterService;
import br.com.labestudo.teste.fixture.SelfRegisterFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static br.com.labestudo.teste.util.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SelfRegisterControllerTest {

    private static final String SELF_REGISTER_API_URL_PATH = "/selfregister";

    private MockMvc mockMvc;

    @Mock
    private SelfRegisterService selfRegisterService;

    @InjectMocks
    private SelfRegisterController selfRegisterController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(selfRegisterController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenPUTIsCalledThenASelfRegisterUserIsCreated() throws Exception {
        // given
        var userDto = SelfRegisterFixture.validUserDto();
        // when
        when(selfRegisterService.selfRegister(userDto)).thenReturn(userDto);
        // then
        mockMvc.perform(put(SELF_REGISTER_API_URL_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userDto)))
                .andExpect(status().isCreated());
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

}
