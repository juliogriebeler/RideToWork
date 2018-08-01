package br.com.juliogriebeler.ridetowork.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.juliogriebeler.ridetowork.exception.FileStorageException;
import br.com.juliogriebeler.ridetowork.exception.MyFileNotFoundException;
import br.com.juliogriebeler.ridetowork.model.UploadFile;
import br.com.juliogriebeler.ridetowork.properties.FileStorageProperties;
import br.com.juliogriebeler.ridetowork.repository.UploadFileRepository;

@Service
public class UploadFileService {

    private final Path uploadFileLocation;
    
    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Autowired
    public UploadFileService(FileStorageProperties fileStorageProperties) {
        this.uploadFileLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.uploadFileLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public UploadFile storeFile(MultipartFile file) {
    	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        try {
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            Path targetLocation = this.uploadFileLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            UploadFile uploadFile = new UploadFile(fileName, fileDownloadUri,
                    file.getContentType(), file.getSize());
            return uploadFileRepository.save(uploadFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.uploadFileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}