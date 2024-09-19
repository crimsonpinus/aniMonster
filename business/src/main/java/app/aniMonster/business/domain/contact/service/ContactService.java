package app.aniMonster.business.domain.contact.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import app.aniMonster.postgresql.db.contact.repository.ContactRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public ContactEntity save(ContactEntity contactEntity) {
        return contactRepository.save(contactEntity);
    }

    public List<ContactEntity> findSocialId(String id) {
        return  contactRepository.findAllBySocialIdOrderByIdDesc(id);
    }

    public List<ContactEntity> findReply() {
        return contactRepository.findAllByAnswerIsNullOrderByIdDesc();
    }

    @Transactional
    public ContactEntity reply(ContactEntity entity) {
        var checkEntity = contactRepository.findFirstByIdAndSocialIdOrderByIdDesc(entity.getId(), entity.getSocialId())
                .orElseThrow(() ->new BusinessException(BusinessErrorCode.NULL_POINT,"Reply Error -Not Found"));

        checkEntity.setAdminId(entity.getAdminId());
        checkEntity.setAnswer(entity.getAnswer());
        checkEntity.setAnswerAt(entity.getAnswerAt());

        return checkEntity;
    }
}
