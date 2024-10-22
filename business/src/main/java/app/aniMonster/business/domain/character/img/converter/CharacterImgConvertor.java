package app.aniMonster.business.domain.character.img.converter;



import app.aniMonster.business.common.annotation.Converter;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.character.img.model.CharacterImgResponse;
import app.aniMonster.business.domain.character.img.model.CharacterImgSingleResponse;
import app.aniMonster.business.domain.file.model.FileResponse;
import app.aniMonster.postgresql.db.character.entity.CharacterEntity;
import app.aniMonster.postgresql.db.character.img.entity.CharacterImgEntity;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgCategory;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class CharacterImgConvertor {

    public CharacterImgEntity toEntity(CharacterEntity characterEntity, FileResponse fileResponse, CharacterImgCategory category
    ) {
        return Optional.ofNullable(characterEntity)
                .map(entity -> {
                    return Optional.ofNullable(fileResponse)
                            .map(response -> {
                                return CharacterImgEntity.builder()
                                        .id(response.getFile_id())
                                        .characterId(entity.getId())
                                        .category(category)
                                        .isActivate(CharacterImgIsActivate.ACTIVATED)
                                        .url(response.getUrl())
                                        .build();
                            })
                            .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"FileResponse is null"));
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"CharacterEntity is null"));

    }
    public CharacterImgSingleResponse toResponse(CharacterImgEntity characterImgEntity) {
        return Optional.ofNullable(characterImgEntity)
                .map(it -> {
                    return CharacterImgSingleResponse.builder()
                            .id(it.getId())
                            .category(it.getCategory())
                            .url(it.getUrl())
                            .build();
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"CharacterImgEntity is null"));
    }
    public CharacterImgResponse toResponse(List<CharacterImgEntity> characterImgEntity) {
        var response = new CharacterImgResponse();

        List<CharacterImgSingleResponse> character_img = new ArrayList<>();
        List<CharacterImgSingleResponse> album_img = new ArrayList<>();

        characterImgEntity.forEach(it -> {
            var singleResponse = toResponse(it);

            switch (singleResponse.getCategory()) {
                case THUMBNAIL -> {
                    response.setThumbnail_img(singleResponse);
                }
                case CHARACTER -> {
                    character_img.add(singleResponse);
                }
                case BACKGROUND -> {
                    response.setBackground_img(singleResponse);
                }
                case ALBUM -> {
                    album_img.add(singleResponse);
                }
            }

        });

        response.setCharacter_img(character_img);
        response.setAlbum_img(album_img);

        return response;
    }
}
