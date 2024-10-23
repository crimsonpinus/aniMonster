package app.aniMonster.business.domain.character.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.character.img.model.CharacterImgResponse;
import app.aniMonster.business.domain.character.model.CharacterModifyRequest;
import app.aniMonster.business.domain.character.model.CharacterRequest;
import app.aniMonster.business.domain.character.model.CharacterResponse;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Converter
public class CharacterConvertor {
    @Autowired
    DbEncryptUtil encryptUtil;

    public CharacterEntity toEntity(CharacterRequest characterRequest) {
        return Optional.ofNullable(characterRequest)
                .map(it -> {
                    return CharacterEntity.builder()
                            .id(UUID.randomUUID())
                            .name(it.getName())
                            .nameKor(it.getName_kor())
                            .gender(it.getGender())
                            .age(it.getAge())
                            .nationality(it.getNationality())
                            .personality(it.getPersonality())
                            .isActivate(CharacterIsActivate.ACTIVATED)
                            .creationId(encryptUtil.encryptEncode("Not Set"))
                            .registeredAt(Instant.now())
                            .description(it.getDescription())
                            .prompt(it.getPrompt())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "CharacterRequest is Null"));

    }

    public CharacterEntity toEntity(CharacterModifyRequest characterModifyRequest) {
        return Optional.ofNullable(characterModifyRequest)
                .map(it -> {
                    return CharacterEntity.builder()
                            .id(it.getId())
                            .name(it.getName())
                            .nameKor(it.getName_kor())
                            .gender(it.getGender())
                            .age(it.getAge())
                            .nationality(it.getNationality())
                            .personality(it.getPersonality())
                            .isActivate(it.getIs_activate())
                            .description(it.getDescription())
                            .prompt(it.getPrompt())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "CharacterRequest is Null"));

    }

    public CharacterResponse toResponse(CharacterEntity characterEntity, CharacterImgResponse imgResponse) {
        return Optional.ofNullable(characterEntity)
                .map(entity -> {
                    return Optional.ofNullable(imgResponse)
                            .map(response -> {
                                return CharacterResponse.builder()
                                        .id(entity.getId())
                                        .name(entity.getName())
                                        .name_kor(entity.getNameKor())
                                        .gender(entity.getGender())
                                        .age(entity.getAge())
                                        .nationality(entity.getNationality())
                                        .personality(entity.getPersonality())
                                        .is_activate(entity.getIsActivate())
                                        .description(entity.getDescription())
                                        .prompt(entity.getPrompt())
                                        .img(response)
                                        .build();
                            })
                            .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "CharacterResponse is Null"));
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "CharacterResponse is Null"));
    }
}
