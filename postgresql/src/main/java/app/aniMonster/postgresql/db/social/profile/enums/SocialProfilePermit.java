package app.aniMonster.postgresql.db.social.profile.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SocialProfilePermit {
    PERMIT("승인"),
    REFUSE("거절")
    ;

    private final String description;
}
