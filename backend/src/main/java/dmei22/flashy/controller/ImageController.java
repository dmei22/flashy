package dmei22.flashy.controller;

import dmei22.flashy.repository.ImageRepository;
import dmei22.flashy.service.ImageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/deck")
@CrossOrigin("http://localhost:4200/")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // CREATE


    // READ


    // UPDATE


    // DELETE

}
