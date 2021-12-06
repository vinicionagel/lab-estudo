package br.com.labestudo.api.service.file;

import br.com.labestudo.api.exception.FileDeleteException;
import br.com.labestudo.api.exception.FileDownloadException;
import br.com.labestudo.api.exception.PathConfigurableException;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FileService implements Uploadable, Downloadable, Deletable, PathConfigurable {

    public static final String ROOT_DIR = File.separator+"dados"+File.separator;

    @Override
    public void configurePath(Long userId) throws PathConfigurableException {
        try {
            Files.createDirectories(Paths.get(MessageFormat.format("{0}{1}", ROOT_DIR, userId)));
        } catch (IOException e) {
            throw new PathConfigurableException(e);
        }
    }

    @Override
    public void upload(String filePath, String fileKey, byte[] fileContent) throws FileUploadException {

    }

    @Override
    public byte[] download(String filePath, String fileKey) throws FileDownloadException {
        return new byte[0];
    }

    @Override
    public void delete(String filePath, String fileKey) throws FileDeleteException {

    }

}
