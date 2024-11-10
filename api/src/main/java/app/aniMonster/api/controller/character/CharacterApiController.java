package app.aniMonster.api.controller.character;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.controller.social.Social;
import app.aniMonster.business.domain.character.business.CharacterBusiness;
import app.aniMonster.business.domain.character.img.model.CharacterImgRequest;
import app.aniMonster.business.domain.character.model.*;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/character")
public class CharacterApiController {
        private final CharacterBusiness characterBusiness;
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
            summary = "***관리자*** 관리자 모드용 케릭터 저장",
            description = """
                    name -> 영문이름 \n
                    name_kor -> 한글이름 \n
                    gender -> MALE, FEMALE, NOT_SET \n
                    age -> 나이 \n
                    nationality -> 국적 \n
                    personality -> 성격(친절, 활발, 어두움...) \n
                    description -> 캐릭터 설명 \n
                    prompt -> System Prompt \n
                    
                    이미지 \n
                        thumbnail -> 대표 이미지
                        character -> 캐릭터 이미지
                        background -> 백그라운드 이미지
                        album -> 앨범 이미지
                    """
    )
    @PostMapping(
            value = "/save",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Api<CharacterResponse> save(
            @Valid
            @RequestPart Api<CharacterRequest> request,
            @RequestPart MultipartFile thumbnail,
            @RequestPart List<MultipartFile> character,
            @RequestPart List<MultipartFile> background,
            @RequestPart List<MultipartFile> album
    ) {
        var imgRequest = CharacterImgRequest.builder()
                .thumbnail(thumbnail)
                .character(character)
                .background(background)
                .album(album)
                .build();

        social.getAdminId();
        var response = characterBusiness.save(request.getBody(), imgRequest);
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "***관리자*** 모든 캐릭터 리스트"
    )
    @PostMapping("/find/all")
    public Api<CharacterAllResponse> findAll(
            @Valid
            @RequestBody Api<String> request
    ) {
        social.getAdminId();
        var response = characterBusiness.findAll();
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "활성화 된 캐릭터 리스트 "
    )
    @PostMapping("/find/all/activate")
    public Api<List<CharacterResponse>> findAllActivate(
            @Valid
            @RequestBody Api<String> request
    ){
        var response = characterBusiness.findAllByIsActivate(true);
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "***관리자*** 비활성화 된 캐릭터 리스트 "
    )
    @PostMapping("/find/all/deactivate")
    public Api<List<CharacterResponse>> findAllDeactivate(
            @Valid
            @RequestBody Api<String> request
    ){
        social.getAdminId();
        var response = characterBusiness.findAllByIsActivate(false);
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "캐릭터이름(영문이름)으로 검색"
    )
    @PostMapping("/find/name")
    public Api<CharacterResponse> findByName(
            @Valid
            @RequestBody Api<CharacterNameRequest> request
    ) {
        social.getAdminId();
        var response = characterBusiness.findByName(request.getBody());
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "캐릭터 ID로 검색"
    )
    @PostMapping("/find/id")
    public Api<CharacterResponse> findById(
            @Valid
            @RequestBody Api<CharacterIdRequest> request
    ) {
        social.getAdminId();
        var response = characterBusiness.findById(request.getBody());
        return social.withToken(response, request.getResult());
    }

    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "캐릭터 정보 수정",
            description = """
                    id를 이용하여 나머지 정보 수정 \n
                        name -> 영문이름 \n
                        name_kor -> 한글이름 \n
                        gender -> MALE, FEMALE, NOT_SET \n
                        age -> 나이 \n
                        nationality -> 국적 \n
                        personality -> 성격(친절, 활발, 어두움...) \n
                        description -> 캐릭터 설명 \n
                        prompt -> System Prompt \n
                    """
    )
    @PostMapping("/modify/id")
    public Api<CharacterResponse> modifyById(
            @Valid
            @RequestBody Api<CharacterModifyRequest> request
    ) {
        social.getAdminId();
        var response = characterBusiness.modifyById(request.getBody());
        return social.withToken(response, request.getResult());
    }
}
