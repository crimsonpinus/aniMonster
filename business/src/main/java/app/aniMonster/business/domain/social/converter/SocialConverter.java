package app.aniMonster.business.domain.social.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.social.entity.SocialEntity;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class SocialConverter {

    @Autowired
    DbEncryptUtil encryptUtil;

    public SocialEntity toEntity(SocialSignRequest signUpRequest){
        return Optional.ofNullable(signUpRequest)
                .map(it -> {
                    return  SocialEntity.builder()
                            .socialId(encryptUtil.encryptEncode(it.getProvider() + "_" + it.getSocial_id()))
                            .name(encryptUtil.encryptEncode(it.getName()))
                            .email(encryptUtil.encryptEncode(it.getEmail()))
                            .provider(it.getProvider())
                            .status(String.valueOf(SocialStatus.REGISTERED))
                            .registeredAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social sign up failed"));
    }

    public SocialResponse toResponse(SocialEntity socialEntity){
        return Optional.ofNullable(socialEntity)
                .map(it ->{
                    return SocialResponse.builder()
                            .socialId(encryptUtil.encryptDecode(it.getSocialId()))
                            .name(encryptUtil.encryptDecode(it.getName()))
                            .email(encryptUtil.encryptDecode(it.getEmail()))
                            .isAdult(it.getIsAdult())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Data Null"));
    }
}
