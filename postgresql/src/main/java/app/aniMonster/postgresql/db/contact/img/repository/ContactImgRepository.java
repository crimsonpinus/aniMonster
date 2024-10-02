package app.aniMonster.postgresql.db.contact.img.repository;

import app.aniMonster.postgresql.db.contact.img.entity.ContactImgEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactImgRepository extends JpaRepository<ContactImgEntity, String> {

    List<ContactImgEntity> findByContactIdOrderByIdAsc(Long contactId);
}
