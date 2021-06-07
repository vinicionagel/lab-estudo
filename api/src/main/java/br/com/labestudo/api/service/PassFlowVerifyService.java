package br.com.labestudo.api.service;

import br.com.labestudo.api.exception.PassValidationException;
import br.com.labestudo.api.model.dto.MessageErrorDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.IntStream;

@Service
public class PassFlowVerifyService {

    public void verifyPass(String pass) throws PassValidationException {
        List<MessageErrorDto> messageErrorDtoList = new ArrayList<>();
        veryfiLenghtPass(pass, messageErrorDtoList);
        var contaisNumber = new AtomicBoolean(false);
        var containsUpperCase = new AtomicBoolean(false);
        var contaisLowerCase = new AtomicBoolean(false);
        IntStream.range(0, pass.length()).forEach(i -> {
            var character = pass.charAt(i);
            containsNumber(character, contaisNumber);
            containsUpperCase(character, containsUpperCase);
            containsLowerCase(character, contaisLowerCase);
        });
        addErrorMessage(messageErrorDtoList, contaisNumber.get(), new MessageErrorDto("pass.noContainsNumber", null));
        addErrorMessage(messageErrorDtoList, containsUpperCase.get(), new MessageErrorDto("pass.noContainsUpperCase", null));
        addErrorMessage(messageErrorDtoList, contaisLowerCase.get(), new MessageErrorDto("pass.noContainsLowerCase", null));
        if (!messageErrorDtoList.isEmpty()) {
            throw new PassValidationException(messageErrorDtoList);
        }
    }

    private void addErrorMessage(List<MessageErrorDto> messageErrorDtoList, boolean value, MessageErrorDto messageErrorDto) {
        if (!value) {
            messageErrorDtoList.add(messageErrorDto);
        }
    }

    private void veryfiLenghtPass(String pass, List<MessageErrorDto> messageErrorDto) {
        var isCorrectLength = pass.length() >= 6 && pass.length() <= 16;
        addErrorMessage(messageErrorDto, isCorrectLength, new MessageErrorDto("pass.lenght.invalid", null));
    }

    private void containsLowerCase(char character, AtomicBoolean contaisLowerCase) {
        if (Character.isLowerCase(character)) {
            contaisLowerCase.set(true);
        }
    }

    private void containsUpperCase(char pass, AtomicBoolean containsUpperCase) {
        if (Character.isUpperCase(pass)) {
            containsUpperCase.set(true);
        }
    }

    private void containsNumber(char pass, AtomicBoolean containsNumber) {
        if (Character.isDigit(pass)) {
            containsNumber.set(true);
        }
    }

}
