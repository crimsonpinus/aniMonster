package app.aniMonster.postgresql.db.notification.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum NotificationCategory {

    NOTIFICATION("공지사항"),
    FAQ("FAQ");

    private final String description;
}
