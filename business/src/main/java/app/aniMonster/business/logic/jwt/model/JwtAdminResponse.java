package app.aniMonster.business.logic.jwt.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class JwtAdminResponse {

    private String admin_token;
    private LocalDateTime access_token_expire_at;

}
