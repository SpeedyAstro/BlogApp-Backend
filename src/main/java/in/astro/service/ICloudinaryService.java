package in.astro.service;

import in.astro.payloads.PostDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface ICloudinaryService {
    public PostDTO upload(MultipartFile file, Integer postId);
}
