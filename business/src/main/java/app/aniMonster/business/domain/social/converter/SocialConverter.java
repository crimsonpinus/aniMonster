package app.aniMonster.business.domain.social.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.social.model.SocialResponse;
import app.aniMonster.business.domain.social.model.SocialSignResponse;
import app.aniMonster.business.domain.social.model.SocialSignRequest;
import app.aniMonster.business.domain.social.model.SocialUpdateRequest;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.social.entity.SocialEntity;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
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
                            .status(SocialStatus.REGISTERED)
                            .isAdult(SocialIsAdult.UNREGISTERED)
                            .registeredAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social sign up failed"));
    }

    public SocialEntity toEntity(SocialUpdateRequest socialUpdateRequest, String socialId){
        return Optional.ofNullable(socialUpdateRequest)
                .map(it -> {
                    return SocialEntity.builder()
                            .socialId(encryptUtil.encryptEncode(socialId))
                            .nick(it.getNick())
                            .status(it.getStatus())
                            .isAdult(it.getIs_adult())
                            .build();


                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social update failed"));
    }


    public SocialEntity toEntity(String id){
        return Optional.ofNullable(id)
                .map(it -> {
                    return SocialEntity.builder()
                            .socialId(encryptUtil.encryptEncode(it))
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social sign up failed"));
    }

    //token 발행시 사용됨
    public SocialEntity toEntity(SocialSignResponse socialSingResponse){
        return Optional.ofNullable(socialSingResponse)
                .map(it -> {
                    return SocialEntity.builder()
                            .socialId(encryptUtil.encryptEncode(it.getSocialId()))
                            .isAdult(it.getIsAdult())
                            .status(it.getStatus())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social sign up failed"));
    }




    public SocialSignResponse toSignResponse(SocialEntity socialEntity){
        return Optional.ofNullable(socialEntity)
                .map(it ->{
                    return SocialSignResponse.builder()
                            .socialId(encryptUtil.encryptDecode(it.getSocialId()))
                            .isAdult(it.getIsAdult())
                            .status(it.getStatus())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Data Null"));
    }

    public SocialResponse toResponse(SocialEntity socialEntity){
        return Optional.ofNullable(socialEntity)
                .map(it ->{
                    return SocialResponse.builder()
                            .socialId(encryptUtil.encryptDecode(it.getSocialId()))
                            .name(encryptUtil.encryptDecode(it.getName()))
                            .email(encryptUtil.encryptDecode(it.getEmail()))
                            .nick(it.getNick())
                            .provider(it.getProvider())
                            .status(it.getStatus())
                            .isAdult(it.getIsAdult())
                            .registeredAt(it.getRegisteredAt())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social User Null"));
    }

}
