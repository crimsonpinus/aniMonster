package app.aniMonster.postgresql.db.social.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialGender {
    MALE("남성"),
    FEMALE("여성"),
    NONE("미설정")
    ;

    private final String description;
}
