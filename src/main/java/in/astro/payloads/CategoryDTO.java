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
    @Size(min = 2)
    private String category_title;
    @NotBlank
    @Size(max = 25)
    private String category_description;
}
