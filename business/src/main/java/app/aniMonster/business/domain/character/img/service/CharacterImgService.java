package app.aniMonster.business.domain.character.img.service;

import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import app.aniMonster.postgresql.db.character.img.repository.CharacterImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CharacterImgService {

    private final CharacterImgRepository characterImgRepository;

    public CharacterImgEntity save(CharacterImgEntity characterImgEntity) {
        return characterImgRepository.save(characterImgEntity);
    }

    public List<CharacterImgEntity> findCharacterId(UUID id) {
        return characterImgRepository.findAllByCharacterIdAndIsActivate(id, CharacterImgIsActivate.ACTIVATED);
    }
}
