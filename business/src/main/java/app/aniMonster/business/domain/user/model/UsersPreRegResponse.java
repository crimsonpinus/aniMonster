package app.aniMonster.business.domain.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersPreRegResponse {

    @NotBlank
    private String mobile;

    private Boolean infoCollection;

    private Boolean recvEvent;

    private Boolean older14;

    private Instant regDate;
}
