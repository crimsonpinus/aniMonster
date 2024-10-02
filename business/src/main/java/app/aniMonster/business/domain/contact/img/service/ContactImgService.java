package app.aniMonster.business.domain.contact.img.service;

import app.aniMonster.postgresql.db.contact.img.entity.ContactImgEntity;
import app.aniMonster.postgresql.db.contact.img.repository.ContactImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactImgService {

    private final ContactImgRepository contactImgRepository;

    public ContactImgEntity save(ContactImgEntity contactImgEntity) {
        return contactImgRepository.save(contactImgEntity);
    }

    public List<ContactImgEntity> findContactId(Long contactId) {
        return contactImgRepository.findByContactIdOrderByIdAsc(contactId);
    }
}
