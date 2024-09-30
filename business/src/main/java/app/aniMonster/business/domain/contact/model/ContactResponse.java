package app.aniMonster.business.domain.contact.model;

import app.aniMonster.postgresql.db.contact.enums.ContactCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactResponse {

    private Long id;

    private String social_id;

    private ContactCategory category;

    private String title;

    private String contents;

    private Instant registered_at;

    private String admin_id;

    private String answer;

    private Instant answer_at;
}
