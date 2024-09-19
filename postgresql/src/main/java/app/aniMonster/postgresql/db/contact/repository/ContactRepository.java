package app.aniMonster.postgresql.db.contact.repository;

import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    List<ContactEntity> findAllBySocialIdOrderByIdDesc(String socialId);
    List<ContactEntity> findAllByAnswerIsNullOrderByIdDesc();
    Optional<ContactEntity> findFirstByIdAndSocialIdOrderByIdDesc(Long id, String socialId);
}
