package in.astro.controller;

import in.astro.payloads.ApiResponse;
import in.astro.payloads.CommentDTO;
import in.astro.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private ICommentService service;
    @PostMapping("/post/{postId}/user/{userId}/comments")
    public ResponseEntity<CommentDTO> createComment(
            @RequestBody CommentDTO commentDTO,
            @PathVariable Integer postId,
            @PathVariable Integer userId
    ){
        CommentDTO comment = this.service.createComment(commentDTO, postId, userId);
        return  new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}/comment")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer id){
        this.service.deleteComment(id);
        return new ResponseEntity<>(new ApiResponse("Successfully Deleted",true),HttpStatus.OK);
    }
}
