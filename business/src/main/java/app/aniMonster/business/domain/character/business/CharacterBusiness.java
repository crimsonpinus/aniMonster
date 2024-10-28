package app.aniMonster.business.domain.character.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.character.converter.CharacterConvertor;
import app.aniMonster.business.domain.character.img.business.CharacterImgBusiness;
import app.aniMonster.business.domain.character.img.model.CharacterImgRequest;
import app.aniMonster.business.domain.character.model.*;
import app.aniMonster.business.domain.character.service.CharacterService;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Business
public class CharacterBusiness {

    private final CharacterService characterService;
    private final CharacterConvertor characterConvertor;
    private final CharacterImgBusiness characterImgBusiness;

    public CharacterResponse save(CharacterRequest characterRequest, CharacterImgRequest characterImgRequest) {
        var entity = characterConvertor.toEntity(characterRequest);
        var usedEntity = characterService.save(entity);

        var imgResponse = characterImgBusiness.save(usedEntity, characterImgRequest);

        var response = characterConvertor.toResponse(usedEntity, imgResponse);
        return response;
    }

    public CharacterAllResponse findAll(){
        var usedEntity = characterService.findAll();
        var activateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate().equals(CharacterIsActivate.ACTIVATED))
                .toList();
        var deactivateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate().equals(CharacterIsActivate.DEACTIVATED))
                .toList();

        var activateResponse = toResponses(activateEntity);
        var deactivateResponse = toResponses(deactivateEntity);

        return CharacterAllResponse.builder()
                .activate(activateResponse)
                .deactivate(deactivateResponse)
                .build();
    }

    public List<CharacterResponse> findAllByIsActivate(Boolean active) {
        var isActive = active ? CharacterIsActivate.ACTIVATED : CharacterIsActivate.DEACTIVATED;
        var usedEntity = characterService.findAllByIsActivate(isActive);

        return toResponses(usedEntity);
    }

    public CharacterResponse findByName(CharacterNameRequest characterNameRequest) {
        var usedEntity = characterService.findByName(characterNameRequest.getName());
        return findImgAndToResponse(usedEntity);

    }

    public CharacterResponse findById(CharacterIdRequest characterIdRequest) {
        var usedEntity = characterService.findById(characterIdRequest.getId());
        return findImgAndToResponse(usedEntity);
    }

    public CharacterResponse modifyById(CharacterModifyRequest characterModifyRequest) {
        var entity = characterConvertor.toEntity(characterModifyRequest);
        var usedEntity = characterService.modifyById(entity);
        return findImgAndToResponse(usedEntity);
    }


    /**
     *
     * @param entity
     * @return
     */
    private CharacterResponse findImgAndToResponse(CharacterEntity entity) {
        var imgResponse = characterImgBusiness.findByCharacterId(entity.getId());
        return characterConvertor.toResponse(entity, imgResponse);
    }

    private List<CharacterResponse> toResponses(List<CharacterEntity> entities) {
        List<CharacterResponse> characterResponses = new ArrayList<>();
        entities.forEach(entity -> {
            var response = findImgAndToResponse(entity);
            characterResponses.add(response);
        });
        return characterResponses;
    }

    }
