package in.astro.payloads;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDTO {
    private int id;
    private String content;
    private UserDTO user;
}
