package app.aniMonster.business.logic.token.helper;

import app.aniMonster.business.common.error.TokenBusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.token.ifs.TokenHelperIfs;
import app.aniMonster.business.logic.token.model.TokenDto;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper implements TokenHelperIfs {
    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.plus-hour}")
    private Long accessTokenHour;

    @Value("${token.refresh-token.plus-hour}")
    private Long refreshTokenHour;


    @Override
    public TokenDto issueAccessToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenHour);

        var expireAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expireAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiresAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public TokenDto issueRefreshToken(Map<String, Object> data) {
        var expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenHour);

        var expireAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expireAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiresAt(expiredLocalDateTime)
                .build();
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            var result = parser.parseClaimsJws(token);
            return new HashMap<String, Object>(result.getBody());
        }catch (Exception e){
            if (e instanceof SignatureException){
                throw new BusinessException(TokenBusinessErrorCode.INVALID_TOKEN, e);
            }else if (e instanceof ExpiredJwtException){
                throw new BusinessException(TokenBusinessErrorCode.EXPIRED_TOKEN, e);
            }else{
                throw new BusinessException(TokenBusinessErrorCode.UNKNOWN_TOKEN, e);
            }
        }
    }
}
