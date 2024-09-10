package app.aniMonster.postgresql.db.user.enums.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AdultVerification {
    ADULT_YES("성인인증"),
    ADULT_NO("미인증"),
    ;

    private final String description;
}