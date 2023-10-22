package in.astro.service;

import in.astro.entity.Post;
import in.astro.payloads.PageResponse;
import in.astro.payloads.PostDTO;

import java.util.List;

public interface IPostService {
    PostDTO createPost(PostDTO dto, Integer userId,Integer categoryId);
    PostDTO updatePost(PostDTO dto,Integer postId);
    void deletePost(Integer postId);
    PageResponse getAllPost(int pageNo, int pageSize, String sortBy,String sort);
    PostDTO getPostById(Integer id);
//    get all post by category
    PageResponse getPostByCategory(int pageNo,int pageSize,Integer category_id);
    PageResponse getPostByUser(int pageNo,int pageSize,Integer userId);
    List<Post> searchPosts(String keyword);
}
