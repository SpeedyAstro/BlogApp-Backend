package in.astro.service.Impl;

import com.cloudinary.Cloudinary;
import in.astro.payloads.PostDTO;
import in.astro.service.ICloudinaryService;
import in.astro.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryImageServiceImpl implements ICloudinaryService {

    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private IPostService postService;

    @Override
    public PostDTO upload(MultipartFile file, Integer postId) {
        try {
            PostDTO postDTO = this.postService.getPostById(postId); // get the post by id
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of()); // upload the image
            postDTO.setImageName((String) data.get("url"));
            return this.postService.updatePost(postDTO,postId);
        } catch (IOException e) {
            throw new RuntimeException("Image Uploading Failed!");
        }
    }
}
