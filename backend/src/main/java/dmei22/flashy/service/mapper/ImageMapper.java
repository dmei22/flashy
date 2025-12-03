package dmei22.flashy.service.mapper;

import dmei22.flashy.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class ImageMapper {

    public static Image fromUpload(MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setFileType(file.getContentType());
        image.setBytes(file.getBytes());

        return image;
    }
}
