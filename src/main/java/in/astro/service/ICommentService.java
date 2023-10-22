package in.astro.service;

import in.astro.payloads.CommentDTO;
import in.astro.payloads.UserDTO;

public interface ICommentService {
    CommentDTO createComment(CommentDTO dto,Integer postId,Integer userId);
    void deleteComment(Integer commentId);
}
