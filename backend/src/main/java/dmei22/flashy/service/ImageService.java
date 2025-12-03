package dmei22.flashy.service;

import dmei22.flashy.model.Deck;
import dmei22.flashy.model.Image;
import dmei22.flashy.repository.DeckRepository;
import dmei22.flashy.repository.ImageRepository;
import dmei22.flashy.service.mapper.ImageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // CREATE
    // TODO: CREATE IMAGE
    public Image upload(MultipartFile file){
        Image image = new Image();
        try {
            image = ImageMapper.fromUpload(file);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        return this.imageRepository.save(image);
    }

    // READ
    public Image getById(Long id) {
        return this.imageRepository.findById(id).get();
    }

    // UPDATE


    // DELETE

}
