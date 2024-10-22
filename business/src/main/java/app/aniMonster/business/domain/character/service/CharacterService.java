package app.aniMonster.business.domain.character.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import app.aniMonster.postgresql.db.character.img.repository.CharacterImgRepository;
import app.aniMonster.postgresql.db.character.repository.CharacterRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterImgRepository characterImgRepository;

    @Transactional
    public CharacterEntity save(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }


    public List<CharacterEntity> findAll() {
        return characterRepository.findAllByIsActivate(CharacterIsActivate.ACTIVATED);
    }

    public CharacterEntity findByName(String name) {
        return characterRepository.findFirstByNameAndIsActivate(name, CharacterIsActivate.ACTIVATED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Name " + name + " not found"));
    }

    public CharacterEntity findById(UUID id) {
        return characterRepository.findFirstByIdAndIsActivate(id, CharacterIsActivate.ACTIVATED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Id " + id + " not found"));
    }
}
