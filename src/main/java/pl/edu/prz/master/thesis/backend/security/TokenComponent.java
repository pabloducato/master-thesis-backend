package pl.edu.prz.master.thesis.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Date;

@Component
public class TokenComponent {

    public String secret = "secret";

    private static final String TOKEN_PREFIX = "Bearer ";

    private final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;

    public Long getIdFromToken(String token) {
        return Long.parseLong(getAllClaimsFromToken(token).getSubject());
    }

    public Date getIssuedAtFromToken(String token) {
        return getAllClaimsFromToken(token).getIssuedAt();
    }

    public String refreshToken(String token) {
        String refreshedToken;
        Date now = new Date();

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(now);
        refreshedToken = Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();

        return refreshedToken;
    }

    public String generateToken(String id, Collection<? extends GrantedAuthority> authorities) {
        String appName = "Erasmus+ Assistant";
        return Jwts.builder()
                .setIssuer(appName)
                .setSubject(id)
                .setIssuedAt(new Date())
                .setExpiration(generateExpirationDate())
                .claim("role", authorities.iterator().next().getAuthority().substring(5))
                .signWith(SIGNATURE_ALGORITHM, secret)
                .compact();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    private Date generateExpirationDate() {
        int expiresIn = 3600;
        return new Date(new Date().getTime() + expiresIn * 1000);
    }

    public String getToken(HttpServletRequest request) {
        String authHeader = "Authorization";
        String header = request.getHeader(authHeader);
        if (header != null) {
            return header.substring(TOKEN_PREFIX.length());
        }
        return null;
    }
}
