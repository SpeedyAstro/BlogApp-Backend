package in.astro.service;

import in.astro.entity.Post;
import in.astro.payloads.PostDTO;

import java.util.List;

public interface IPostService {
    PostDTO createPost(PostDTO dto, Integer userId,Integer categoryId);
    Post updatePost(PostDTO dto,Integer postId);
    void deletePost(Integer postId);
    List<Post> getAllPost();
    Post getPostById(Integer id);
//    get all post by category
    List<Post> getPostByCategory(Integer category_id);
    List<Post> getPostByUser(Integer userId);
    List<Post> searchPosts(String keyword);
}
