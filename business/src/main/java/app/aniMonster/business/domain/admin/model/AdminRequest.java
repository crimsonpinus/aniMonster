package app.aniMonster.business.domain.admin.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AdminRequest {

    @NotBlank
    public String admin_id;

    @NotBlank
    public String admin_password;
}
