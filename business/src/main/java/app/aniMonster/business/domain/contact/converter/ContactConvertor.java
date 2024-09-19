package app.aniMonster.business.domain.contact.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.contact.model.ContactAdminRequest;
import app.aniMonster.business.domain.contact.model.ContactConsumerRequest;
import app.aniMonster.business.domain.contact.model.ContactResponse;
import app.aniMonster.business.logic.encrypt.DbEncryptUtil;
import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class ContactConvertor {

    @Autowired
    DbEncryptUtil encryptUtil;

    public ContactEntity toEntity(ContactConsumerRequest contactConsumerRequest) {
        return Optional.ofNullable(contactConsumerRequest)
                .map(it -> {
                    return ContactEntity.builder()
                            .socialId(encryptUtil.encryptEncode(it.getSocial_id()))
                            .category(it.getCategory())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .registeredAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Consumer Request Null"));
    }

    public ContactEntity toEntity(ContactAdminRequest contactAdminRequest, String adminId) {
        return Optional.ofNullable(contactAdminRequest)
                .map(it -> {
                    return ContactEntity.builder()
                            .id(it.getId())
                            .socialId(encryptUtil.encryptEncode(it.getSocial_id()))
                            .adminId(adminId)
                            .answer(it.getAnswer())
                            .answerAt(Instant.now())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Admin Request Null"));
    }
    public ContactEntity toEntity(String socialId){
        return Optional.ofNullable(socialId)
                .map(it -> {
                    return ContactEntity.builder()
                            .socialId(encryptUtil.encryptEncode(socialId))
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Id Null"));
    }

    public ContactResponse toResponse(ContactEntity contactEntity) {
        return Optional.ofNullable(contactEntity)
                .map(it -> {
                    return ContactResponse.builder()
                            .id(it.getId())
                            .socialId(encryptUtil.encryptDecode(it.getSocialId()))
                            .category(it.getCategory())
                            .title(it.getTitle())
                            .contents(it.getContents())
                            .registeredAt(it.getRegisteredAt())
                            .adminId(it.getAdminId())
                            .answer(it.getAnswer())
                            .answerAt(it.getAnswerAt())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"ContactEntity is Null"));
    }
}
