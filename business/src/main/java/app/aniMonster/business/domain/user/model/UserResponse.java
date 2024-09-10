package app.aniMonster.business.domain.user.model;

import app.aniMonster.postgresql.db.user.enums.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private Long id;

    private String name;

    private String email;

    private UserStatus status;

    private String address;

    private Instant registeredAt;

    private Instant unregisterdeAt;

    private Instant lastLogin;

}
