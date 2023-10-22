package in.astro.service;

import in.astro.entity.Category;
import in.astro.entity.Post;
import in.astro.entity.User;
import in.astro.exceptions.ResourceNotFoundException;
import in.astro.payloads.PageResponse;
import in.astro.payloads.PostDTO;
import in.astro.repository.CategoryRepository;
import in.astro.repository.PostRepository;
import in.astro.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public PostDTO updatePost(PostDTO dto, Integer postId) {
        Post post = this.postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "ID", postId));
        post.setContent(dto.getContent());
        post.setTitle(dto.getTitle());
        post.setImageName(dto.getImageName());
        Post save = this.postRepository.save(post);
        return this.mapper.map(save,PostDTO.class);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "post", postId));
        this.postRepository.delete(post);
    }

    @Override
    public PageResponse getAllPost(int pageNo,int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Post> postPage = this.postRepository.findAll(pageable);
//        List<Post> content = postPage.getContent();
//        List<PostDTO> postDTOList = content
//                .stream().map(post -> this.mapper.map(post, PostDTO.class))
//                .collect(Collectors.toList());
//        PageResponse response = new PageResponse();
//        response.setContent(postDTOList);
//        response.setPageNumber(postPage.getNumber());
//        response.setPageSize(postPage.getSize());
//        response.setTotalElements(postPage.getTotalElements());
//        response.setTotalPages(postPage.getTotalPages());
//        response.setLastPage(postPage.isLast());
        return getPageResponse(postPage);
    }

    @Override
    public PostDTO getPostById(Integer id) {
        Post post = this.postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Posts", "ID", id));
        return this.mapper.map(post,PostDTO.class);
    }

//    Get list of posts by Category id
    @Override
    public PageResponse getPostByCategory(int pageNo,int pageSize,Integer category_id) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Category category = this.categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", category_id));
        Page<Post> postPage = this.postRepository.findByCategory(category, pageable);
//        List<Post> posts = postPage.getContent();
//        List<PostDTO> postDTOList = posts.stream().map(post -> this.mapper.map(post, PostDTO.class))
//                .collect(Collectors.toList());
//        PageResponse response = new PageResponse();
//        response.setContent(postDTOList);
//        response.setPageNumber(postPage.getNumber());
//        response.setPageSize(postPage.getSize());
//        response.setTotalElements(postPage.getTotalElements());
//        response.setTotalPages(postPage.getTotalPages());
//        response.setLastPage(postPage.isLast());
        return getPageResponse(postPage);
    }

    @Override
    public PageResponse getPostByUser(int pageNo,int pageSize,Integer userId) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "UserId", userId));
        Page<Post> byUser = this.postRepository.findByUser(user,pageable);
//        List<PostDTO> postDTOList = byUser.stream().map(post -> this.mapper.map(post, PostDTO.class))
//                .collect(Collectors.toList());
        return getPageResponse(byUser);
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return null;
    }

    public PageResponse getPageResponse(Page<Post> postPage){
        List<Post> posts = postPage.getContent();
        List<PostDTO> postDTOList = posts.stream().map(post -> this.mapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        PageResponse response = new PageResponse();
        response.setContent(postDTOList);
        response.setPageNumber(postPage.getNumber());
        response.setPageSize(postPage.getSize());
        response.setTotalElements(postPage.getTotalElements());
        response.setTotalPages(postPage.getTotalPages());
        response.setLastPage(postPage.isLast());
        return  response;
    }
}
