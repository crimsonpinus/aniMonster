package app.aniMonster.postgresql.db.file.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FileIsActivate {

    ACTIVATED("활성화"),
    DEACTIVATED("비활성화");

    private final String description;
}
