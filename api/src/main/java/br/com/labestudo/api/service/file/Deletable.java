package br.com.labestudo.api.service.file;

import br.com.labestudo.api.exception.FileDeleteException;

public interface Deletable {

    void delete(String filePath, String fileKey) throws FileDeleteException;

}
