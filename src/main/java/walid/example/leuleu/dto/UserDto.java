package walid.example.leuleu.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDto {
    private String username;
    private String password;
    private String role;
}