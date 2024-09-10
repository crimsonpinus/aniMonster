package app.aniMonster.postgresql.db.user.enums.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {

    GENDER_MALE("He/Him"),
    GENDER_FEMALE("She/Her"),
    GENDER_UNKNOWN("Unknown");
    ;

    private final String description;
}
