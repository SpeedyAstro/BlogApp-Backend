package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.PostDTO;
import in.astro.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByUser(@PathVariable Integer userId){
        List<PostDTO> postByUser = this.service.getPostByUser(userId);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDTO> postByUser = this.service.getPostByCategory(categoryId);
        return new ResponseEntity<>(postByUser,HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDTO>> getAllPosts(){
        List<PostDTO> posts = this.service.getAllPost();
        return new ResponseEntity<>(posts,HttpStatus.OK);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Integer id){
        PostDTO postDTO = this.service.getPostById(id);
        return new ResponseEntity<>(postDTO,HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO dto,@PathVariable Integer id){
        PostDTO dto1 = this.service.updatePost(dto, id);
        return new ResponseEntity<>(dto1,HttpStatus.OK);
    }
    @DeleteMapping("/post/{id}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer id){
        this.service.deletePost(id);
        return new ResponseEntity<>(new ApiResponse("Successfully Deleted with id "+id,true),HttpStatus.OK);
    }
}
