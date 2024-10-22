package app.aniMonster.postgresql.db.character.img.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharacterImgIsActivate {

    ACTIVATED("활성화"),
    DEACTIVATED("비활성화");

    private final String description;
}
