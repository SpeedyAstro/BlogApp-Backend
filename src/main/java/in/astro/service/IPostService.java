package in.astro.service;

import in.astro.entity.Post;
import in.astro.payloads.PostDTO;

import java.util.List;

public interface IPostService {
    PostDTO createPost(PostDTO dto, Integer userId,Integer categoryId);
    PostDTO updatePost(PostDTO dto,Integer postId);
    void deletePost(Integer postId);
    List<PostDTO> getAllPost();
    PostDTO getPostById(Integer id);
//    get all post by category
    List<PostDTO> getPostByCategory(Integer category_id);
    List<PostDTO> getPostByUser(Integer userId);
    List<Post> searchPosts(String keyword);
}
