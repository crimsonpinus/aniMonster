package app.aniMonster.business.domain.contact.service;

import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import app.aniMonster.postgresql.db.contact.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public ContactEntity save(ContactEntity contactEntity) {
        return contactRepository.save(contactEntity);
    }
}
