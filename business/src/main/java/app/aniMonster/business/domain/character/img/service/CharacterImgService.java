package app.aniMonster.business.domain.character.img.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import app.aniMonster.postgresql.db.character.img.repository.CharacterImgRepository;
import jakarta.transaction.Transactional;
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

    public List<CharacterImgEntity> findCharacterIdAll(UUID id) {
        return characterImgRepository.findAllByCharacterId(id);
    }

    @Transactional
    public CharacterImgEntity update(CharacterImgEntity entity) {
        var characterImgEntityCheck = characterImgRepository.findById(entity.getId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Character img does not exist"));
        if (entity.getIsActivate() != null) {
            characterImgEntityCheck.setIsActivate(entity.getIsActivate());
        }
        return characterImgEntityCheck;
    }


}
