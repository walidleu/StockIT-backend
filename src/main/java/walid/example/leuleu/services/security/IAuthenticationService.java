package walid.example.leuleu.services.security;

import walid.example.leuleu.dto.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse accessToken(String username, String password);
    AuthenticationResponse refreshToken(String refreshToken);

}