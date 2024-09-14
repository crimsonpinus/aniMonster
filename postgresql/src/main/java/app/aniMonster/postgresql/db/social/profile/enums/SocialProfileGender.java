package app.aniMonster.postgresql.db.social.profile.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialProfileGender {
    MALE("남성"),
    FEMALE("여성"),
    NOT_SET("미설정")
    ;

    private final String description;
}
