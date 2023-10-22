package in.astro.service.Impl;

import in.astro.entity.Comment;
import in.astro.entity.Post;
import in.astro.entity.User;
import in.astro.exceptions.ResourceNotFoundException;
import in.astro.payloads.CommentDTO;
import in.astro.repository.CommentRepository;
import in.astro.repository.PostRepository;
import in.astro.repository.UserRepository;
import in.astro.service.ICommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements ICommentService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;
    @Override
    public CommentDTO createComment(CommentDTO dto, Integer postId, Integer userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "PostId", postId));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Comment comment = this.mapper.map(dto, Comment.class);
        comment.setUser(user);
        comment.setPost(post);
        Comment save = commentRepository.save(comment);
        return this.mapper.map(save,CommentDTO.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "ID", commentId));
        this.commentRepository.delete(comment);
    }
}
