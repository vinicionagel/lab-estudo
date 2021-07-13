package br.com.labestudo.teste.service;

import br.com.labestudo.api.model.dto.EmailDto;
import br.com.labestudo.api.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class EmailServiceTest {


    @InjectMocks
    private EmailService emailService;

    @Test
    void whenExecuteSendEmailThenSendEmail() {
        assertDoesNotThrow(() -> emailService.send(EmailDto.builder().build()));

    }
}
