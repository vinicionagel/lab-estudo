package br.com.labestudo.api.service.file;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

public interface Uploadable {

    void upload(String filePath, String fileKey, byte[] fileContent) throws FileUploadException;

}
