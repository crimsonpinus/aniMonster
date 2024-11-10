package app.aniMonster.postgresql.db.character.img.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharacterImgIsSelected {

    SELECTED("활성화"),
    UNSELECTED("비활성화");

    private final String description;
}
