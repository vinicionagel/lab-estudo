package br.com.labestudo.api.service;

import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.model.dto.MessagesErrorDto;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;

@Service
public class PassFlowVerifyService {

    public void verifyPass(String pass) throws PassValidationException {
        Set<MessagesErrorDto> messagesErrorDtoList = new LinkedHashSet<>();
        veryfiLenghtPass(pass, messagesErrorDtoList);
        for (int i = 0; i < pass.length(); i++) {
            var character = pass.charAt(i);
            containsNumber(character, messagesErrorDtoList);
            containsUpperCase(character, messagesErrorDtoList);
            containsLowerCase(character, messagesErrorDtoList);
        }
        if (!messagesErrorDtoList.isEmpty()) {
            throw new PassValidationException(messagesErrorDtoList);
        }
    }

    private void veryfiLenghtPass(String pass, Set<MessagesErrorDto> messagesErrorDto) {
        var isCorrectLength = pass.length() >= 6 && pass.length() <= 16;
        if (!isCorrectLength) {
            messagesErrorDto.add(new MessagesErrorDto("fdsfds", null));
        }
    }

    private void containsLowerCase(char character, Set<MessagesErrorDto> messagesErrorDto) {
        if (Character.isLowerCase(character)) {
            messagesErrorDto.add(new MessagesErrorDto("vfdsfdsfs", null));
        }
    }

    private void containsUpperCase(char pass, Set<MessagesErrorDto> messagesErrorDto) {
        if (Character.isUpperCase(pass)) {
            messagesErrorDto.add(new MessagesErrorDto("vfdsfdsfs", null));
        }
    }

    private void containsNumber(char pass, Set<MessagesErrorDto> messagesErrorDto) {
        if (Character.isDigit(pass)) {
            messagesErrorDto.add(new MessagesErrorDto("vfdsfdsfs", null));
        }
    }

}
