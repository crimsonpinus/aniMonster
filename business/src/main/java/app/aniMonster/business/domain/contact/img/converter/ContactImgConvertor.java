package app.aniMonster.business.domain.contact.img.converter;

import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.contact.img.model.ContactImgResponse;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.business.domain.file.model.FileResponse;
import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import app.aniMonster.postgresql.db.contact.img.entity.ContactImgEntity;
import app.aniMonster.postgresql.db.file.enums.FileCategory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class ContactImgConvertor {

    public FileRequest toFileRequest(ContactEntity contactEntity) {
        return Optional.ofNullable(contactEntity)
                .map(it -> {
                    return FileRequest.builder()
                            .upload_id(contactEntity.getSocialId())
                            .category(FileCategory.CONTACT)
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Contact entity is null"));
    }

    public ContactImgEntity toEntity(ContactEntity contactEntity, FileResponse fileResponse) {
        return Optional.ofNullable(contactEntity)
                .map(entity -> {
                    return Optional.ofNullable(fileResponse)
                            .map(response -> {
                                return ContactImgEntity.builder()
                                        .id(response.getFile_id())
                                        .contactId(entity.getId())
                                        .url(response.getUrl())
                                        .build();
                            })
                            .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"File Response is Null"));
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Contact entity is null"));
    }

    public ContactImgResponse toResponse(ContactImgEntity contactImgEntity) {
        return Optional.ofNullable(contactImgEntity)
                .map(it -> {
                    return ContactImgResponse.builder()
                            .url(it.getUrl())
                            .id(it.getId())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Contact Img entity is null"));
    }

    public List<ContactImgResponse> toResponseList(List<ContactImgEntity> contactImgEntities) {
        List<ContactImgResponse> contactImgResponses = new ArrayList<>();
        contactImgEntities.forEach(it -> {
            contactImgResponses.add(toResponse(it));
        });
        return contactImgResponses;
    }
}
