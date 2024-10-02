package app.aniMonster.business.domain.contact.img.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.contact.img.converter.ContactImgConvertor;
import app.aniMonster.business.domain.contact.img.model.ContactImgResponse;
import app.aniMonster.business.domain.contact.img.service.ContactImgService;
import app.aniMonster.business.domain.file.business.FileBusiness;
import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Business
public class ContactImgBusiness {

    private final ContactImgConvertor contactImgConvertor;
    private final ContactImgService contactImgService;
    private final FileBusiness fileBusiness;


    public List<ContactImgResponse> save(ContactEntity contactEntity, List<MultipartFile> files) {

        List<ContactImgResponse> result = new ArrayList<>();

        //upload files
        var fileRequest = contactImgConvertor.toFileRequest(contactEntity);
        var fileResponse = fileBusiness.upload(fileRequest, files);


        fileResponse.forEach(res -> {
            var entity = contactImgConvertor.toEntity(contactEntity, res);
            var usedEntity = contactImgService.save(entity);
            result.add(contactImgConvertor.toResponse(usedEntity));
        });

        return result;
    }

    public List<ContactImgResponse> find(ContactEntity contactEntity) {
        List<ContactImgResponse> result = new ArrayList<>();

        var usedEntityList = contactImgService.findContactId(contactEntity.getId());
        var resultList = contactImgConvertor.toResponseList(usedEntityList);

        return resultList;
    }

    public ContactImgResponse deActive(String id) {
        return null;
    }
}
