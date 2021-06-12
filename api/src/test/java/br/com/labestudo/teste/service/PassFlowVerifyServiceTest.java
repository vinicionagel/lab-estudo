package br.com.labestudo.teste.service;

import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.model.dto.MessageErrorDto;
import br.com.labestudo.api.service.PassFlowVerifyService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PassFlowVerifyServiceTest {

    private PassFlowVerifyService passFlowVerifyService = new PassFlowVerifyService();

    @Test
    void WhenPassValid_ThenSuccess() {
        assertDoesNotThrow(() -> passFlowVerifyService.verifyPass("213AAbb"));
    }

    @Test
    void WhenPassLenghtInvalid_ThenPassValidationException() {
        PassValidationException passValidationException = assertThrows(
                PassValidationException.class,
                () -> passFlowVerifyService.verifyPass("---")
        );
        Assertions.assertThat(passValidationException.getMessageErrorDtoList())
                .extracting(MessageErrorDto::getKey)
                .contains("pass.lenght.invalid", "pass.containsNumber.invalid", "pass.containsUpperCase.invalid", "pass.containsLowerCase.invalid");
    }

}
