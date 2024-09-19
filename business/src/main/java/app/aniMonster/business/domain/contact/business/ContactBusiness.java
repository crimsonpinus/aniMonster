package app.aniMonster.business.domain.contact.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.contact.converter.ContactConvertor;
import app.aniMonster.business.domain.contact.model.ContactAdminRequest;
import app.aniMonster.business.domain.contact.model.ContactConsumerRequest;
import app.aniMonster.business.domain.contact.model.ContactResponse;
import app.aniMonster.business.domain.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
@Slf4j
public class ContactBusiness {

    private final ContactService contactService;
    private final ContactConvertor contactConvertor;

    public ContactResponse save(ContactConsumerRequest contactConsumerRequest) {
        var entity = contactConvertor.toEntity(contactConsumerRequest);
        var usedEntity = contactService.save(entity);
        var response = contactConvertor.toResponse(usedEntity);
        return response;
    }

    public List<ContactResponse> findSocialId(String id) {
        var entity = contactConvertor.toEntity(id);
        var listEntity = contactService.findSocialId(entity.getSocialId());
        var response = listEntity.stream().map(contactConvertor::toResponse)
                .collect(Collectors.toList());
        return response;
    }

    public List<ContactResponse> findReply() {
        var listEntity = contactService.findReply();
        var response = listEntity.stream().map(contactConvertor::toResponse)
                .collect(Collectors.toList());
        return response;
    }

    public ContactResponse reply(ContactAdminRequest contactAdminRequest, String admin_id) {
        var entity = contactConvertor.toEntity(contactAdminRequest, admin_id);
        var usedEntity = contactService.reply(entity);
        var response = contactConvertor.toResponse(usedEntity);
        return response;
    }


}
