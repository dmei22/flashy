package dmei22.flashy.controller;

import dmei22.flashy.model.Image;
import dmei22.flashy.service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/image")
@CrossOrigin("http://localhost:4200/")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // CREATE


    // READ
    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getById(@PathVariable("id") Long id) {
        Image image = this.imageService.getById(id);

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(image.getFileType()))
            .body(image.getBytes());
    }

    // UPDATE


    // DELETE

}
