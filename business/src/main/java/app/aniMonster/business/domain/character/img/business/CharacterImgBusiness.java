package app.aniMonster.business.domain.character.img.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.character.converter.CharacterConvertor;
import app.aniMonster.business.domain.character.img.converter.CharacterImgConvertor;
import app.aniMonster.business.domain.character.img.model.CharacterImgRequest;
import app.aniMonster.business.domain.character.img.model.CharacterImgResponse;
import app.aniMonster.business.domain.character.img.model.CharacterImgSingleResponse;
import app.aniMonster.business.domain.character.img.service.CharacterImgService;
import app.aniMonster.business.domain.file.business.FileBusiness;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.business.domain.file.model.FileResponse;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgCategory;
import app.aniMonster.postgresql.db.file.enums.FileCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Business
public class CharacterImgBusiness {

    private final FileBusiness fileBusiness;

    private final CharacterImgConvertor characterImgConvertor;
    private final CharacterImgService characterImgService;

    public CharacterImgResponse save(CharacterEntity character, CharacterImgRequest characterImgRequest) {
        var fileRequest = FileRequest.builder()
                .upload_id(character.getId().toString())
                .category(FileCategory.CHARACTER)
                .info(character.getName())
                .build();



        List<CharacterImgEntity> characterImgEntities = new ArrayList<>();

        if (characterImgRequest.getThumbnail() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getThumbnail());
            var entity = characterImgConvertor.toEntity(character, fileResponse, CharacterImgCategory.THUMBNAIL);

            characterImgEntities.add(entity);
        }

        if (characterImgRequest.getCharacter() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getCharacter());
            List<CharacterImgSingleResponse> resp = new ArrayList<>();
            fileResponse.forEach(fileRes -> {
                var entity = characterImgConvertor.toEntity(character, fileRes, CharacterImgCategory.CHARACTER);

                characterImgEntities.add(entity);
            });
        }

        if (characterImgRequest.getBackground() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getBackground());
            var entity = characterImgConvertor.toEntity(character, fileResponse, CharacterImgCategory.BACKGROUND);

            characterImgEntities.add(entity);
        }

        if (characterImgRequest.getAlbum() != null) {
            var fileResponse = fileBusiness.upload(fileRequest, characterImgRequest.getAlbum());
            List<CharacterImgSingleResponse> resp = new ArrayList<>();
            fileResponse.forEach(fileRes -> {
                var entity = characterImgConvertor.toEntity(character, fileRes, CharacterImgCategory.ALBUM);

                characterImgEntities.add(entity);
            });
        }

        return characterImgConvertor.toResponse(characterImgEntities);

    }

    public CharacterImgResponse findByCharacterId(UUID characterId) {
        var usedEntity = characterImgService.findCharacterId(characterId);
        var response = characterImgConvertor.toResponse(usedEntity);
        return response;
    }



    private CharacterImgSingleResponse entitySaveConvertor(CharacterImgEntity characterImgEntity) {
        var usedEntity = characterImgService.save(characterImgEntity);
        return characterImgConvertor.toResponse(usedEntity);
    }


}
