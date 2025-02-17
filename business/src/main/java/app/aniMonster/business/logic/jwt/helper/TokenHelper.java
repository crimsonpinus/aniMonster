package app.aniMonster.business.logic.jwt.helper;

import app.aniMonster.business.common.error.TokenBusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.jwt.model.TokenModel;
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
public class TokenHelper implements TokenHelperIfs{
    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.access-token.plus-hour}")
    private Long accessTokenHour;

    @Value("${jwt.refresh-token.plus-hour}")
    private Long refreshTokenHour;

    private final Long ADMIN_TOKEN_HOUR = 8L;

    @Override
    public TokenModel issueAccessToken(Map<String, Object> data) {
        return makeToken(data, accessTokenHour);
    }

    @Override
    public TokenModel issueRefreshToken(Map<String, Object> data) {
        return makeToken(data, refreshTokenHour);
    }

    @Override
    public TokenModel issueAdminToken(Map<String, Object> data) {
        return makeToken(data, ADMIN_TOKEN_HOUR);
    }

    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {

        // 비밀키로 HMAC-SHA 키 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
        // JWT 파서 빌더 생성
        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();
        try {
            // 토큰 파싱 및 클레임 추출
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

    /**
     * 토큰생성 메서드
     * @param data
     * @param tokenHour
     * @return
     */
    public TokenModel makeToken(Map<String, Object> data, Long tokenHour){
        var expiredLocalDateTime = LocalDateTime.now()
                .plusHours(tokenHour);

        var expireAt = Date.from(
                expiredLocalDateTime.atZone(
                        ZoneId.systemDefault()
                ).toInstant()
        );

        // 비밀키로 HMAC-SHA 키 생성
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        // JWT 빌더를 사용하여 토큰 생성
        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expireAt)
                .compact();
        return TokenModel.builder()
                .token(jwtToken)
                .expiresAt(expiredLocalDateTime)
                .build();

    }
}

