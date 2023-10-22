package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.PageResponse;
import in.astro.payloads.PostDTO;
import in.astro.service.ICloudinaryService;
import in.astro.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

import static in.astro.config.AppConstants.*;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private IPostService service;

    @Autowired
    private ICloudinaryService cloudinaryService;


//    Create New Post
    @PostMapping("/user/{userid}/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO post,
                                              @PathVariable Integer userid,
                                              @PathVariable Integer categoryId){

        PostDTO dto = this.service.createPost(post, userid, categoryId);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

//    Get Posts by User
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<PageResponse> getPostByUser(
            @RequestParam(defaultValue = PAGE_NUMBER ,required = false) int pageNo,
            @RequestParam(defaultValue = PAGE_SIZE,required = false) int pageSize,
            @PathVariable Integer userId){
        PageResponse response = this.service.getPostByUser(pageNo,pageSize,userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    Get Posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PageResponse> getPostByCategory(
            @RequestParam(defaultValue = PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(defaultValue = PAGE_SIZE,required = false) int pageSize,
            @PathVariable Integer categoryId){
        PageResponse response = this.service.getPostByCategory(pageNo,pageSize,categoryId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PageResponse> getAllPosts(
            @RequestParam(defaultValue = PAGE_NUMBER,required = false) int pageNo,
            @RequestParam(defaultValue = PAGE_SIZE,required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = SORT_BY,required = false) String sortBy,
            @RequestParam(value = "sort",defaultValue = SORT_DIR,required = false) String sort
    ){
        PageResponse response = this.service.getAllPost(pageNo,pageSize,sortBy,sort);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    Get a specific post
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

    @GetMapping("/posts/search/{title}")
    public ResponseEntity<List<PostDTO>> searchByTitle(@PathVariable String title){
        List<PostDTO> postDTOS = this.service.searchPosts(title);
        return new ResponseEntity<>(postDTOS,HttpStatus.OK);
    }

    @PostMapping("/post/image/{postId}")
    public ResponseEntity<PostDTO> uploadImage(@RequestParam MultipartFile image,@PathVariable Integer postId){
        PostDTO dto = this.cloudinaryService.upload(image,postId);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

}
