package in.astro.service;

import in.astro.entity.Category;
import in.astro.entity.Post;
import in.astro.entity.User;
import in.astro.exceptions.ResourceNotFoundException;
import in.astro.payloads.PostDTO;
import in.astro.repository.CategoryRepository;
import in.astro.repository.PostRepository;
import in.astro.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class PostServiceImpl implements IPostService{
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostDTO createPost(PostDTO dto,Integer userId,Integer categoryId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Post post = mapper.map(dto, Post.class);
        post.setImageName("default.png");
        post.setPostAdded(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post save = this.postRepository.save(post);
        return mapper.map(save,PostDTO.class);
    }

    @Override
    public Post updatePost(PostDTO dto, Integer postId) {
        return null;
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post", postId));
        this.postRepository.delete(post);
    }

    @Override
    public List<Post> getAllPost() {
        return this.postRepository.findAll();
    }

    @Override
    public Post getPostById(Integer id) {
        return null;
    }

    @Override
    public List<Post> getPostByCategory(Integer category_id) {
        return null;
    }

    @Override
    public List<Post> getPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }
}
