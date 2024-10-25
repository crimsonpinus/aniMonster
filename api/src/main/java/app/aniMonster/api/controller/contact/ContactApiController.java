package app.aniMonster.api.controller.contact;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.common.error.UserErrorCode;
import app.aniMonster.api.common.exception.ApiException;
import app.aniMonster.api.controller.social.Social;
import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.business.domain.contact.business.ContactBusiness;
import app.aniMonster.business.domain.contact.model.ContactAdminRequest;
import app.aniMonster.business.domain.contact.model.ContactConsumerRequest;
import app.aniMonster.business.domain.contact.model.ContactResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contact")
public class ContactApiController {

    private final ContactBusiness contactBusiness;

    private final Social social;

    /**
     *
     * @param request
     * @param files
     * @return
     */
    @Operation(
            summary = "최초 문의하기 저장",
            description = """
                    **하위 값은  아래의 값만 허용\n
                        - category = ABOUT_TECHNICAL_SUPPORT("기술지원"), ABOUT_PERSONAL_PROTECTION("개인정보 보호 및 보안 관련 문의"), COMMENT_FEEDBACK("의견 및 피드백"), ABOUT_SOCIAL("계정관련 문의"), REPORT_BUG_ERROR("오류 및 버그 신고") 
                    """
    )
    @PostMapping(
            value = "/save",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public Api<ContactResponse> faq(
            @Valid
            @RequestPart Api<ContactConsumerRequest> request,
            @RequestPart(value = "images", required = false) List<MultipartFile> files
    ) {

        var response = contactBusiness.save(request.getBody(), files);
        return social.withToken(response, request.getResult());
    }

    @Operation(
            summary = "로그인 한 ID 관련 문의글 표출",
            description = """
                    로그인 관련 Id 글 최신순으로 정렬
                    """
    )
    @PostMapping("/findSocialId")
    public Api<List<ContactResponse>> findSocialId(
            @Valid
            @RequestBody Api<String> request
    ){
        var response = contactBusiness.findSocialId(social.getSocialId());

        return social.withToken(response, request.getResult());
    }


    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "관리자 응답 달지 않은 글 검색",
            description = """
                    최신순으로 정렬되어 보여짐
                    관리자로 접속시에만 접근 가능
                    """
    )
    @PostMapping("/find/reply")
    public Api<List<ContactResponse>> findReply(
            @Valid
            @RequestBody Api<String> request
    ){
        social.getAdminId();

        var response = contactBusiness.findReply();

        return social.withToken(response, request.getResult());
    }


    /**
     *
     * @param request
     * @return
     */
    @Operation(
            summary = "문의사항 답변",
            description = """
                    관리자로 접속시에만 작동\n
                    """
    )
    @PostMapping("/reply")
    public Api<ContactResponse> reply(
            @Valid
            @RequestBody Api<ContactAdminRequest> request
    ) {
        var adminId = social.getAdminId();
        var response = contactBusiness.reply(request.getBody(), adminId);

        return social.withToken(response, request.getResult());
    }
}
