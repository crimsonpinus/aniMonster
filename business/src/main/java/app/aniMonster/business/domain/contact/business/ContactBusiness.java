package app.aniMonster.business.domain.contact.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.contact.converter.ContactConvertor;
import app.aniMonster.business.domain.contact.model.ContactConsumerRequest;
import app.aniMonster.business.domain.contact.model.ContactResponse;
import app.aniMonster.business.domain.contact.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Business
@Slf4j
public class ContactBusiness {

    private final ContactService contactService;
    private final ContactConvertor cotentConvertor;

    public ContactResponse save(ContactConsumerRequest contactConsumerRequest) {
        var entity = cotentConvertor.toEntity(contactConsumerRequest);
        var usedEntity = contactService.save(entity);
        var response = cotentConvertor.toResponse(usedEntity);
        return response;
    }
}
