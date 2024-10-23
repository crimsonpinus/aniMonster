package app.aniMonster.api.controller.character.img;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.controller.social.Social;
import app.aniMonster.business.domain.character.business.CharacterBusiness;
import app.aniMonster.business.domain.character.img.business.CharacterImgBusiness;
import app.aniMonster.business.domain.character.img.model.*;
import app.aniMonster.business.domain.character.model.CharacterIdRequest;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/character/img")
public class CharacterImgApiController {

    private final CharacterImgBusiness characterImgBusiness;
    private final Social social;

    /**
     *
     * @param request
     * @param thumbnail
     * @param character
     * @param background
     * @param album
     * @return
     */
    @Operation(
            summary = "캐릭터 관련 파일 수정",
            description = """
                    character_id를 통해 정보 수정 및 파일 업데이트 가능 \n
                    character_img_info_list는 tbl_character_img 관련 정보 리스트가 담겨져 있음 \n
                        id -> id
                        is_activate -> ACTIVATED, DEACTIVATED
                        
                        변경할 것이 없고 파일만 업로드 시 []로보내면 됨
                    """
    )
    @PostMapping(
            value = "/modify/id",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Api<CharacterImgResponseAll> modifyByCharacterId(
            @Valid
            @RequestPart Api<CharacterImgModifyRequset> request,
            @RequestPart(required = false) MultipartFile thumbnail,
            @RequestPart(required = false) List<MultipartFile> character,
            @RequestPart(required = false) MultipartFile background,
            @RequestPart(required = false) List<MultipartFile> album
    ) {
        var imgRequest = CharacterImgRequest.builder()
                .thumbnail(thumbnail)
                .character(character)
                .background(background)
                .album(album)
                .build();
        var response = characterImgBusiness.modifyByCharacterId(request.getBody(), imgRequest);
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "character_id를 이용하여 관련 이미지 검색",
            description = """
                    활성화 이미지와 비활성화 이미지를 분리하여 보여줌
                    """
    )
    @PostMapping("/find/id")
    public Api<CharacterImgIdResponse> findByCharacterId(
            @Valid
            @RequestBody Api<CharacterIdRequest> request
    ) {
        var response = characterImgBusiness.findByCharacterIdAll(request.getBody());
        return social.withToken(response, request.getResult());
    }
}

