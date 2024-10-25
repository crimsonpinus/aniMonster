package app.aniMonster.postgresql.db.admin.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminIsActivate {

    ACTIVATED("활성화"),
    DEACTIVATED("비활성화");

    private final String description;
}
