package app.aniMonster.postgresql.db.social.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지"),
    ;

    private final String description;
}
