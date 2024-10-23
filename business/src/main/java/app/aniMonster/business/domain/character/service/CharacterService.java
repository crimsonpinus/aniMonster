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

    @Transactional
    public CharacterEntity save(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }


    public List<CharacterEntity> findAll(CharacterIsActivate activate) {
        return characterRepository.findAllByIsActivate(activate);
    }

    public CharacterEntity findByName(String name) {
        return characterRepository.findFirstByNameAndIsActivate(name, CharacterIsActivate.ACTIVATED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Name " + name + " not found"));
    }

    public CharacterEntity findById(UUID id) {
        return characterRepository.findFirstByIdAndIsActivate(id, CharacterIsActivate.ACTIVATED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Id " + id + " not found"));
    }

    @Transactional
    public CharacterEntity modifyById(CharacterEntity entity) {
        var characterEntityCheck = characterRepository.findFirstById(entity.getId())
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Id " + entity.getId() + " not found"));

        if(entity.getName() != null) {
            characterEntityCheck.setName(entity.getName());
        }
        if(entity.getNameKor() != null) {
            characterEntityCheck.setNameKor(entity.getNameKor());
        }
        if(entity.getGender() != null) {
            characterEntityCheck.setGender(entity.getGender());
        }
        if(entity.getAge() != null) {
            characterEntityCheck.setAge(entity.getAge());
        }
        if(entity.getNationality() != null) {
            characterEntityCheck.setNationality(entity.getNationality());
        }
        if(entity.getPersonality() != null) {
            characterEntityCheck.setPersonality(entity.getPersonality());
        }
        if(entity.getIsActivate() != null) {
            characterEntityCheck.setIsActivate(entity.getIsActivate());
        }
        if(entity.getDescription() != null) {
            characterEntityCheck.setDescription(entity.getDescription());
        }
        if(entity.getPrompt() != null) {
            characterEntityCheck.setPrompt(entity.getPrompt());
        }

        return characterEntityCheck;
    }
}
