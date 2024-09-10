package app.aniMonster.business.domain.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersPreRegRequest {

    @NotBlank
    private String mobile;

    private Boolean infoCollection;

    private Boolean recvEvent;

    private Boolean older14;

    private Instant regDate;
}
