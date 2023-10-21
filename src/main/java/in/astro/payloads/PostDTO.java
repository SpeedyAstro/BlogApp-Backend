package in.astro.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    private Integer post_id;
    private String title;
    private String content;
    private String imageName;
    private Date postAdded;
    private CategoryDTO category;
    private UserDTO user;
}
