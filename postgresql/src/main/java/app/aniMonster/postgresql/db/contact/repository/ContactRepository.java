package app.aniMonster.postgresql.db.contact.repository;

import app.aniMonster.postgresql.db.contact.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    Optional<List<ContactEntity>> findBySocialIdOrderByIdDesc(String socialId);
}
