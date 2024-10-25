package app.aniMonster.postgresql.db.admin.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdminAuthor {

    ADMIN("관리자 등급"),
    NORMAL("기본 등급");

    private final String description;
}
