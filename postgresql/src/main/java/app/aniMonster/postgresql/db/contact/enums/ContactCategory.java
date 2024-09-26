package app.aniMonster.postgresql.db.contact.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ContactCategory {

    ABOUT_TECHNICAL_SUPPORT("기술지원"),
    ABOUT_PERSONAL_PROTECTION("개인정보 보호 및 보안 관련 문의"),
    COMMENT_FEEDBACK("의견 및 피드백"),
    ABOUT_SOCIAL("계정관련 문의"),
    REPORT_BUG_ERROR("오류 및 버그 신고")
    ;

    private final String description;
}
