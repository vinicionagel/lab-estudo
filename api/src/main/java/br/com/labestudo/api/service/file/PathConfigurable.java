package br.com.labestudo.api.service.file;

import br.com.labestudo.api.exception.PathConfigurableException;

public interface PathConfigurable {

    void configurePath(Long userId) throws PathConfigurableException;

}
