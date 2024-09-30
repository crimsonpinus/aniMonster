package app.aniMonster.business.logic.jwt.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtResponse {

    private String access_token;
    private LocalDateTime access_token_expire_at;

    private String refresh_token;
    private LocalDateTime refresh_token_expire_at;
}
