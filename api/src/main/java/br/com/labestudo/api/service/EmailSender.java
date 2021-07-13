package br.com.labestudo.api.service;

import br.com.labestudo.api.model.dto.EmailDto;
import freemarker.template.TemplateException;

import java.io.IOException;

public interface EmailSender {

    void send(EmailDto emailDto) throws TemplateException, IOException;

}
