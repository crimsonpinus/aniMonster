package app.aniMonster.business.domain.file.model;

import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileModifyRequest {
    public String file_id;

    public FileIsActivate is_activate;

    public String info;
}
