package app.aniMonster.business.domain.user.converter;

import app.aniMonster.business.common.annotation.Converter;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.business.domain.user.model.UserLoginRequest;
import app.aniMonster.business.domain.user.model.UserRegisterRequset;
import app.aniMonster.business.domain.user.model.UserResponse;
import app.aniMonster.postgresql.db.user.entity.UserEntity;
import app.aniMonster.postgresql.db.user.enums.user.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {

    @Autowired
    DbEncryptUtil eu;

    public UserEntity toEntity(UserRegisterRequset requset) {
        return Optional.ofNullable(requset)
                .map(it -> {
                    return UserEntity.builder()
                            .name(eu.encryptEncode(it.getName()))
                            .email(eu.encryptEncode(it.getEmail()))
                            .password(eu.encryptEncode(it.getPassword()))
                            .address(it.getAddress())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "UserTestRegisterRequest null"));
    }

    public UserEntity toEntity(UserLoginRequest requset) {
        return Optional.ofNullable(requset)
                .map(it -> {
                    return UserEntity.builder()
                            .email(eu.encryptEncode(it.getEmail()))
                            .password(eu.encryptEncode(it.getPassword()))
                            .status(UserStatus.REGISTERED)
                            .lastLogin(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "UserTestRegisterRequest null"));
    }

    public UserResponse toResponse(UserEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(it.getId())
                            .name(eu.encryptDecode(it.getName()))
                            .email(eu.encryptDecode(it.getEmail()))
                            .status(it.getStatus())
                            .address(it.getAddress())
                            .registeredAt(it.getRegisteredAt())
                            .unregisterdeAt(it.getUnregisterdeAt())
                            .lastLogin(it.getLastLogin())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "UserTest  null"));

    }
}
