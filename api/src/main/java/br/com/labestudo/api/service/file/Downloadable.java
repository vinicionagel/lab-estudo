package br.com.labestudo.api.service.file;

import br.com.labestudo.api.exception.FileDownloadException;

public interface Downloadable {

    byte[] download(String filePath, String fileKey) throws FileDownloadException;
}
