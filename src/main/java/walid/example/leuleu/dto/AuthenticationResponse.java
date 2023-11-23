package walid.example.leuleu.dto;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthenticationResponse {
    private Boolean success;
    private String error;
    private String accessToken;
    private String refreshToken;


    public AuthenticationResponse(String token, boolean success, String error) {
        this.accessToken = token;
        this.success = success;
        this.error = error;
    }

    public AuthenticationResponse(boolean success, String error) {
        this.success = success;
        this.error = error;
    }

    public AuthenticationResponse(boolean success, String token, String refreshToken) {
        this.success = success;
        this.accessToken = token;
        this.refreshToken = refreshToken;
    }
}