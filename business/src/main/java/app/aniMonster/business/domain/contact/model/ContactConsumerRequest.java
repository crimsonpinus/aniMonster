package app.aniMonster.business.domain.contact.model;

import app.aniMonster.postgresql.db.contact.enums.ContactCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactConsumerRequest {

    @NotBlank
    private String social_id;

    @NotBlank
    private ContactCategory category;

    @NotBlank
    private String title;

    @NotBlank
    private String contents;
}
