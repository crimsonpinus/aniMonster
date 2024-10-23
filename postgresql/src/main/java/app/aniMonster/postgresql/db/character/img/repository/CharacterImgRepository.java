package app.aniMonster.postgresql.db.character.img.repository;

import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CharacterImgRepository extends JpaRepository<CharacterImgEntity, String> {
    List<CharacterImgEntity> findAllByCharacterIdAndIsActivate(UUID id, CharacterImgIsActivate isActivate);
    List<CharacterImgEntity> findAllByCharacterId(UUID characterId);
}
