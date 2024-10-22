package app.aniMonster.postgresql.db.character.img.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CharacterImgCategory {

    THUMBNAIL("대표이미지"),
    CHARACTER("캐릭터이미지"),
    BACKGROUND("백그라운드이미지"),
    ALBUM("앨범"),
    ;
    private final String description;
}
