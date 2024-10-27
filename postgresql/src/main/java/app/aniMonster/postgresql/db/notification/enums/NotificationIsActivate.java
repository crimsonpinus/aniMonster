package app.aniMonster.postgresql.db.notification.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationIsActivate {

    ACTIVATED("활성화"),
    DEACTIVATED("비활성화");

    private final String description;
}
