package in.astro.payloads;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer category_id;
    @NotBlank
    @Size(min = 2, message = "size of title should be more than 2")
    private String category_title;
    @NotBlank
    @Size(min = 10,message = "size should be greater than 10")
    private String category_description;
}
