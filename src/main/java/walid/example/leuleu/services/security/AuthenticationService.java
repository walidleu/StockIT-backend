package walid.example.leuleu.services.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import walid.example.leuleu.dto.AuthenticationResponse;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static walid.example.leuleu.Constant.*;

@Service
public class AuthenticationService implements  IAuthenticationService{
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthenticationService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public AuthenticationResponse accessToken(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password)
        );

        Instant instant = Instant.now();
        String scope = authentication
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        return getAccessAndRefreshToken(instant,username,scope);
    }

    @Override
    public AuthenticationResponse refreshToken(String refreshToken) {
        Jwt decodeJwt = jwtDecoder.decode(refreshToken);
        String subject = decodeJwt.getSubject();

        UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
        String scope = userDetails
                .getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Instant instant = Instant.now();

        return  getAccessAndRefreshToken(instant,subject,scope);
    }

    private JwtClaimsSet jwtGenerator(Instant instant, String subject, String scope, boolean isRefreshToken){
        Instant instantExpire = Boolean.TRUE.equals(isRefreshToken)
                ? instant.plus(EXPIRE_REFRESH_TOKEN, ChronoUnit.DAYS)
                : instant.plus(EXPIRE_ACCESS_TOKEN, ChronoUnit.MINUTES);

        return JwtClaimsSet
                .builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instantExpire)
                .issuer(JWT_ISSUER)
                .claim(JWT_SCOPE,scope)
                .build();
    }

    private AuthenticationResponse getAccessAndRefreshToken(Instant instant,String subject,String scope){
        JwtClaimsSet jwtClaimsSet = jwtGenerator(instant,subject,scope,false);
        JwtClaimsSet jwtClaimsSetRefresh = jwtGenerator(instant,subject,scope,true);

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
        return new AuthenticationResponse(true,jwtAccessToken,jwtRefreshToken);
    }


}