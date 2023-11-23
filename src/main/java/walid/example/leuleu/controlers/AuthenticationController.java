package walid.example.leuleu.controlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import walid.example.leuleu.dto.AuthenticationRequest;
import walid.example.leuleu.dto.AuthenticationResponse;
import walid.example.leuleu.dto.RefreshTokenRequest;
import walid.example.leuleu.services.security.IAuthenticationService;

@RestController
@CrossOrigin
@RequestMapping("/api/auth/v1/")
public class AuthenticationController {
    private final IAuthenticationService authenticationService;

    public AuthenticationController(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/token")
    public ResponseEntity<AuthenticationResponse> jwtToken(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.accessToken(authenticationRequest.getUsername(),authenticationRequest.getPassword()));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> jwtToken( @RequestBody RefreshTokenRequest refreshTokenRequest){
        return ResponseEntity.ok(authenticationService.refreshToken(refreshTokenRequest.getRefreshToken()));
    }
}
