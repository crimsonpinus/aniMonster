package app.aniMonster.business.domain.contact.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAdminRequest {

    @NotBlank
    private Long id;

    @NotBlank
    private String social_id;

//    @NotBlank
//    private String answer_admin_id;

    @NotBlank
    private String answer;

}
