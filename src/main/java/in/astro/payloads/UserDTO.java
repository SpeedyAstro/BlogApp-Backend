package in.astro.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Data
public class UserDTO {
    private int id;

    @NotEmpty
    @Size(min = 4, message = "Username must be at least of size 4")
    private String name;
    @Email(message = "Email address is not valid")
    private String email;
    @NotEmpty
    @Size(min = 3, max = 10, message = "password must be min of 3 and max of 10")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "Minimum eight characters, at least one uppercase letter, one lowercase letter and one number and no special characters")
    private String password;
    @NotEmpty
    private String about;
}
