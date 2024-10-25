package app.aniMonster.business.domain.admin.model;

import app.aniMonster.postgresql.db.admin.enums.AdminAuthor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AdminResponse {

    public UUID id;

    public AdminAuthor admin_author;
}
