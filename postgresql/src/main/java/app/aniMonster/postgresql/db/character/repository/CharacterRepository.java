package app.aniMonster.postgresql.db.character.repository;

import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CharacterRepository extends JpaRepository<CharacterEntity, String> {
    List<CharacterEntity> findAllByIsActivate(CharacterIsActivate isActivate);
    Optional<CharacterEntity> findFirstByNameAndIsActivate(String name, CharacterIsActivate isActivate);
    Optional<CharacterEntity> findFirstByIdAndIsActivate(UUID id, CharacterIsActivate isActivate);

}
