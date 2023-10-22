package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.PageResponse;
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
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "5",required = false) int pageSize,
            @PathVariable Integer userId){
        PageResponse response = this.service.getPostByUser(pageNo,pageSize,userId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    Get Posts by Category
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<PageResponse> getPostByCategory(
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "5",required = false) int pageSize,
            @PathVariable Integer categoryId){
        PageResponse response = this.service.getPostByCategory(pageNo,pageSize,categoryId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

//    Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<PageResponse> getAllPosts(
            @RequestParam(defaultValue = "0",required = false) int pageNo,
            @RequestParam(defaultValue = "5",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "post_id",required = false) String sortBy,
            @RequestParam(value = "sort",defaultValue = "asc",required = false) String sort
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
}
