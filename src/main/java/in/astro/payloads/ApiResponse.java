package in.astro.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private boolean flag;
}
