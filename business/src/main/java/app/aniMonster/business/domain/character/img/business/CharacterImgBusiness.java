package app.aniMonster.business.domain.character.img.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.character.img.converter.CharacterImgConvertor;
import app.aniMonster.business.domain.character.img.model.*;
import app.aniMonster.business.domain.character.img.service.CharacterImgService;
import app.aniMonster.business.domain.character.model.CharacterIdRequest;
import app.aniMonster.business.domain.character.service.CharacterService;
import app.aniMonster.business.domain.file.business.FileBusiness;
import app.aniMonster.business.domain.file.model.FileFindRequest;
import app.aniMonster.business.domain.file.model.FileModifyRequest;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgCategory;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import app.aniMonster.postgresql.db.file.enums.FileCategory;
import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Business
public class CharacterImgBusiness {

    private final FileBusiness fileBusiness;

    private final CharacterService characterService;
    private final CharacterImgConvertor characterImgConvertor;
    private final CharacterImgService characterImgService;

    public CharacterImgResponse save(CharacterEntity character, CharacterImgRequest characterImgRequest) {
        var characterImgEntities = saveFileAndReturnCharacterImgEntityList(character, characterImgRequest);

        return characterImgConvertor.toResponse(characterImgEntities);

    }

    public CharacterImgResponse findByCharacterId(UUID characterId) {
        var usedEntity = characterImgService.findCharacterId(characterId);
        var response = characterImgConvertor.toResponse(usedEntity);
        return response;
    }

    public CharacterImgIdResponse findByCharacterIdAll(CharacterIdRequest characterIdRequest) {
        var usedEntity = characterImgService.findCharacterIdAll(characterIdRequest.getId());

        List<CharacterImgEntity> activateList  = new ArrayList<>();
        List<CharacterImgEntity> deActivateList = new ArrayList<>();
        usedEntity.forEach(it -> {
            switch (it.getIsActivate()) {
                case ACTIVATED -> activateList.add(it);
                case DEACTIVATED -> deActivateList.add(it);
            }
        });

        var activateResponse = characterImgConvertor.toResponse(activateList);
        var deActivateResponse = characterImgConvertor.toResponseAll(deActivateList);

        return CharacterImgIdResponse.builder()
                .activate(activateResponse)
                .deactivate(deActivateResponse)
                .build();
    }

    public CharacterImgResponseAll modifyByCharacterId(CharacterImgModifyRequset characterImgModifyRequset, CharacterImgRequest imgRequest) {

        var characterEntity = characterService.findById(characterImgModifyRequset.getCharacter_id());


        var entityResponseList = saveFileAndReturnCharacterImgEntityList(characterEntity, imgRequest);

        var characterImgInfoList = characterImgModifyRequset.getCharacter_img_info_list();
        if (characterImgInfoList != null) {
            characterImgInfoList.forEach(it -> {
                var entity = characterImgConvertor.toEntity(it);
                var usedEntity = characterImgService.update(entity);
                entityResponseList.add(usedEntity);
                var isActivate = usedEntity.getIsActivate() == CharacterImgIsActivate.ACTIVATED ? FileIsActivate.ACTIVATED : FileIsActivate.DEACTIVATED;
                var fileRequest = FileModifyRequest.builder()
                        .file_id(usedEntity.getId())
                        .is_activate(isActivate)
                        .build();
                fileBusiness.activationSwitch(fileRequest);
            });
        }


        var response = characterImgConvertor.toResponseAll(entityResponseList);
        return response;
    }


    /**
     *
     * @param character
     * @param characterImgRequest
     * @return
     */
    private List<CharacterImgEntity> saveFileAndReturnCharacterImgEntityList(CharacterEntity character, CharacterImgRequest characterImgRequest) {
        var fileRequest = FileRequest.builder()
                .upload_id(character.getId().toString())
                .category(FileCategory.CHARACTER)
                .info(character.getName())
                .build();

        List<CharacterImgEntity> characterImgEntities = new ArrayList<>();

        if (characterImgRequest.getThumbnail() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getThumbnail());
            var entity = characterImgConvertor.toEntity(character, fileResponse, CharacterImgCategory.THUMBNAIL);
            var usedEntity = characterImgService.save(entity);

            characterImgEntities.add(usedEntity);
        }

        if (characterImgRequest.getCharacter() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getCharacter());
            List<CharacterImgSingleResponse> resp = new ArrayList<>();
            fileResponse.forEach(fileRes -> {
                var entity = characterImgConvertor.toEntity(character, fileRes, CharacterImgCategory.CHARACTER);
                var usedEntity = characterImgService.save(entity);

                characterImgEntities.add(usedEntity);
            });
        }

        if (characterImgRequest.getBackground() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getBackground());
            var entity = characterImgConvertor.toEntity(character, fileResponse, CharacterImgCategory.BACKGROUND);
            var usedEntity = characterImgService.save(entity);

            characterImgEntities.add(usedEntity);
        }

        if (characterImgRequest.getAlbum() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getAlbum());
            List<CharacterImgSingleResponse> resp = new ArrayList<>();
            fileResponse.forEach(fileRes -> {
                var entity = characterImgConvertor.toEntity(character, fileRes, CharacterImgCategory.ALBUM);
                var usedEntity = characterImgService.save(entity);

                characterImgEntities.add(usedEntity);
            });
        }
        return characterImgEntities;
    }



}
