package app.aniMonster.postgresql.db.user.enums.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum BrowserTheme {
    THEME_LIGHT("light"),
    THEME_DARK("dark"),
    ;

    private final String description;
}
