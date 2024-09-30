package app.aniMonster.postgresql.db.file.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FileCategory {
    CONTACT("문의 하기"),
    SOCIAL("개인 이미지"),
    CHARACTER("캐릭터 이미지"),
    BACKGROUND("배경 이미지")
    ;

    private final String description;
}
