package app.aniMonster.postgresql.db.character.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharacterGender {
    MALE("남성"),
    FEMALE("여성"),
    NOT_SET("미설정")
    ;

    private final String description;
}
