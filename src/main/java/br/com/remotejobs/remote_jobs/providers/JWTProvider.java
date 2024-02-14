package br.com.remotejobs.remote_jobs.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {
    @Value("${securiry.token.secret}")
    private String secretKey;

    public String validateToken(String token) {
        if (token == null || token.isEmpty()) {
            return "";
        }

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        try {
            var subject = JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();

            return subject;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String getRole(String token) throws Exception {
        if (token == null || token.isEmpty()) {
            throw new Exception("Token is null or empty");
        }

        token = token.replace("Bearer ", "");
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        DecodedJWT jwt;
        try {
            jwt = JWT.require(algorithm)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException e) {
            throw new Exception("Token verification failed", e);
        }

        var claims = jwt.getClaims();
        var rolesClaim = claims.get("roles");

        if (rolesClaim.isNull() || rolesClaim.asArray(String.class).length == 0) {
            throw new Exception("Token does not contain roles claim");
        }

        return rolesClaim.asArray(String.class)[0];
    }

}
