package app.aniMonster.postgresql.db.social.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialIsAdult {
    UNREGISTERED("N"),
    REGISTERED("Y"),
    ;

    private final String description;
}
