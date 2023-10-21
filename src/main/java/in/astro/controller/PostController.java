package in.astro.controller;

import in.astro.payloads.PostDTO;
import in.astro.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private IPostService service;

    @PostMapping("/user/{userid}/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post,
                                              @PathVariable Integer userid,
                                              @PathVariable Integer categoryId){

        PostDTO dto = this.service.createPost(post, userid, categoryId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
