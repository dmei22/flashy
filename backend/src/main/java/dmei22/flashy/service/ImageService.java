package dmei22.flashy.service;

import dmei22.flashy.model.Image;
import dmei22.flashy.repository.ImageRepository;
import dmei22.flashy.service.mapper.ImageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    // CREATE
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
    public Image getById(Long imageId) {
        return this.imageRepository.findById(imageId).get();
    }

    // UPDATE
    public Image update(Long imageId, MultipartFile file) {
        Image image = this.imageRepository.findById(imageId).get();
        try {
            image = ImageMapper.fromUpload(file);
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

        return this.imageRepository.save(image);
    }

    // DELETE

}
