package app.aniMonster.postgresql.db.social.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialStatus {
    UNREGISTERED("해지"),
    REGISTERED("등록"),
    ;

    private final String description;
}
