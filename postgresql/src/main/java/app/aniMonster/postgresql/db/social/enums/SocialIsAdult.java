package app.aniMonster.postgresql.db.social.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialIsAdult {
    REGISTERED("Y"),
    UNREGISTERED("N"),
    ;

    private final String description;
}
