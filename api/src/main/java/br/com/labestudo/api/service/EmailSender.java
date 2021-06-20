package br.com.labestudo.api.service;

import br.com.labestudo.api.model.dto.EmailDto;

public interface EmailSender {

    void send(EmailDto emailDto);

}
