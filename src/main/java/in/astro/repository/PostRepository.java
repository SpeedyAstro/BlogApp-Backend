package in.astro.repository;

import in.astro.entity.Category;
import in.astro.entity.Post;
import in.astro.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {
    Page<Post> findByUser(User user, Pageable pageable);
//    List<Post> findByCategory(Category category);

    Page<Post> findByCategory(Category category, Pageable pageable);
    List<Post> findByTitleContaining(String title);
}
