package app.aniMonster.business.domain.character.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.character.converter.CharacterConvertor;
import app.aniMonster.business.domain.character.img.business.CharacterImgBusiness;
import app.aniMonster.business.domain.character.img.model.CharacterImgRequest;
import app.aniMonster.business.domain.character.model.CharacterIdRequest;
import app.aniMonster.business.domain.character.model.CharacterNameRequest;
import app.aniMonster.business.domain.character.model.CharacterRequest;
import app.aniMonster.business.domain.character.model.CharacterResponse;
import app.aniMonster.business.domain.character.service.CharacterService;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import lombok.Builder;
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

    public List<CharacterResponse> findAll() {
        var usedEntity = characterService.findAll();


        List<CharacterResponse> characterResponses = new ArrayList<>();
        usedEntity.forEach(entity -> {
            var response = findImgAndToResponse(entity);
            characterResponses.add(response);
        });

        return characterResponses;
    }

    public CharacterResponse findByName(CharacterNameRequest characterNameRequest) {
        var usedEntity = characterService.findByName(characterNameRequest.getName());
        return findImgAndToResponse(usedEntity);

    }

    public CharacterResponse findById(CharacterIdRequest characterIdRequest) {
        var usedEntity = characterService.findById(characterIdRequest.getId());
        return findImgAndToResponse(usedEntity);
    }

    private CharacterResponse findImgAndToResponse(CharacterEntity entity) {
        var imgResponse = characterImgBusiness.findByCharacterId(entity.getId());
        return characterConvertor.toResponse(entity, imgResponse);
    }


}
