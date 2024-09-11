package app.aniMonster.business.logic.jwt.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String accessToken;
    private LocalDateTime accessTokenExpireAt;

    private String refreshToken;
    private LocalDateTime refreshTokenExpireAt;
}
