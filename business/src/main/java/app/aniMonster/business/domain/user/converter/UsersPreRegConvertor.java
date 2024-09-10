package app.aniMonster.business.domain.user.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.user.model.UsersPreRegRequest;
import app.aniMonster.business.domain.user.model.UsersPreRegResponse;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.user.entity.UsersPreReg;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UsersPreRegConvertor {
    @Autowired
    DbEncryptUtil eu;

    public UsersPreReg toEntity(UsersPreRegRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return UsersPreReg.builder()
                            .mobile(eu.encryptEncode(it.getMobile()))
                            .personalInfoCollention(Boolean.TRUE)
                            .receiveEvent(Boolean.TRUE)
                            .older14years(Boolean.TRUE)
                            .regDate(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "UserPreReg null"));
    }
    public String toEntity(String mobile) {
        return Optional.ofNullable(mobile)
                .map(it -> {
                    return eu.encryptEncode(it);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Mobile Null"));
    }


    public UsersPreRegResponse toResponse(UsersPreReg entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return UsersPreRegResponse.builder()
                            .mobile(eu.encryptDecode(it.getMobile()))
                            .infoCollection(it.getPersonalInfoCollention())
                            .recvEvent(it.getReceiveEvent())
                            .older14(it.getOlder14years())
                            .regDate(it.getRegDate())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "UserPreReg  null"));


    }

}
