package app.aniMonster.api.controller.file;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.file.business.FileBusiness;
import app.aniMonster.business.domain.file.model.FileFindRequest;
import app.aniMonster.business.domain.file.model.FileRequest;
import app.aniMonster.business.domain.file.model.FileResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/file")
public class fileApiController {

    private final FileBusiness fileBusiness;

    @Operation (
            summary = "파일 업로드",
            description = """
                    json 방식이 아닌 파일 업로드 영향으로 form 형식으로 보내야 함 \n
                        id는 업로드 관련된 id를 사용
                        info는 CHARACTER일때만 입력(캐릭터 명)
                        category 는 아래의 값 사용
                            CONTACT("문의 하기"),
                            SOCIAL("개인 이미지"),
                            CHARACTER("캐릭터 이미지"),
                            BACKGROUND("배경 이미지")
                    """
    )
    @PostMapping(
            value = "/upload",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Api<List<FileResponse>> upload(
            @Valid
            @RequestPart Api<FileRequest> request,
            @RequestPart(value = "image", required = false) List<MultipartFile> files
    ) {
        var responses = fileBusiness.upload(request.getBody(), files);
        return Api.OK(responses);
    }

    @Operation (
            summary = "파일 정보 찾기",
            description = """
                    비활성화(deactivate)한 파일은 검색되지 않음 \n
                        file not found 오류 발생
                    """
    )
    @PostMapping("/find")
    public Api<FileResponse> find(
            @Valid
            @RequestBody Api<FileFindRequest> request
    ) {
        var response = fileBusiness.find(request.getBody());
        return Api.OK(response);
    }

    @Operation (
            summary = "파일 비활성화",
            description = """
                    파일 비활성화만 가능 \n
                        한번 비 활성화 시 활성화 불가능
                        file_id -> 파일 확장자를 뺀 이름
                    """
    )
    @PostMapping("/deactivate")
    public Api<FileResponse> deactivate(
            @Valid
            @RequestBody Api<FileFindRequest> request
    ) {
        var response = fileBusiness.deactivate(request.getBody());
        return Api.OK(response);
    }
}
