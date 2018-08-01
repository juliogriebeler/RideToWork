package br.com.juliogriebeler.ridetowork.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.juliogriebeler.ridetowork.model.UploadFile;
import br.com.juliogriebeler.ridetowork.service.UploadFileService;

@RestController
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private UploadFileService fileStorageService;

    @PostMapping("/uploadFileWithDescription")
	@CrossOrigin
    public UploadFile uploadFileWithDescription(@RequestParam("file") MultipartFile file, @RequestParam("description") String description) {
        return fileStorageService.storeFile(file);
    }
    
    @PostMapping("/uploadFile")
	@CrossOrigin
    public UploadFile uploadFile(@RequestParam("file") MultipartFile file) {
        
        
        return fileStorageService.storeFile(file);
    }

    @PostMapping("/uploadMultipleFiles")
	@CrossOrigin
    public List<UploadFile> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
	@CrossOrigin
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

    	Resource resource = fileStorageService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }
        if(contentType == null) {
            contentType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}