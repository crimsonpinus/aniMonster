package app.aniMonster.api.controller.notification;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.api.controller.social.Social;
import app.aniMonster.business.domain.notification.business.NotificationBusiness;
import app.aniMonster.business.domain.notification.model.*;
import app.aniMonster.business.domain.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notification")
public class NotificationApiController {

    private final NotificationBusiness notificationBusiness;
    private final Social social;

    /**
     *
     * @param faqRequest
     * @return
     */
    @Operation(
            summary = "***관리자*** faq 저장",
            description = """
                    검색시 priority 기준 내림차순 정렬
                    """
    )
    @PostMapping("/faq/save")
    public Api<NotificationFaqResponse> saveFaq(
            @Valid
            @RequestBody Api<NotificationFaqRequest> faqRequest
    ) {
        social.getAdminId();
        var response = notificationBusiness.saveFaq(faqRequest.getBody());
        return Api.OK(response);
    }

    @Operation(
            summary = "***관리자*** faq 수정",
            description = """
                    id 를 사용하여 업데이트 이루어짐\n
                    필요없는 인자는 삭제 가능\n
                    is_activate는 ACTIVATED, DEACTIVATED
                    """
    )
    @PostMapping("/faq/modify")
    public Api<NotificationFaqResponse> modifyFaq(
            @Valid
            @RequestBody Api<NotificationFaqModifyRequest> faqRequest
    ) {
        social.getAdminId();
        var response = notificationBusiness.modifyFaq(faqRequest.getBody());
        return Api.OK(response);
    }

    @Operation(
            summary = "활성화 된 faq 리스트 "
    )
    @PostMapping("/faq/find/")
    public Api<List<NotificationFaqResponse>> findFaqs(
            @Valid
            @RequestBody Api<String> faqRequest
    ) {
        var response = notificationBusiness.getFaqs();
        return social.withToken(response, faqRequest.getResult());
    }

    @Operation(
            summary = "***관리자*** 모든 faq 리스트"
    )
    @PostMapping("/faq/find/all")
    public Api<NotificationFaqAllResponse> findFaqsAll(
            @Valid
            @RequestBody Api<String> faqRequest
    ) {
        social.getAdminId();
        var response = notificationBusiness.getFaqsAll();
        return Api.OK(response);
    }


    /**
     *
     * @param notiRequest
     * @return
     */
    @Operation(
            summary = "***관리자*** 공지사항 저장",
            description = """
                    limit_at 현재 UTC 기준 9시간 - 해야함
                    """
    )
    @PostMapping("/noti/save")
    public Api<NotificationNotiResponse> saveNoti(
            @Valid
            @RequestBody Api<NotificationNotiRequest> notiRequest
    ) {
        social.getAdminId();
        var response = notificationBusiness.saveNoti(notiRequest.getBody());
        return Api.OK(response);
    }

    @Operation(
            summary = "***관리자*** 공지사항 변경",
            description = """
                    limit_at 현재 UTC 기준 9시간 - 해야함\n
                    is_activate는 ACTIVATED, DEACTIVATED\n
                    limit_at을 미래시간으로 할경우 is_activate는 자동 활성화
                    """
    )
    @PostMapping("/noti/modify")
    public Api<NotificationNotiResponse> modifyNoti(
            @Valid
            @RequestBody Api<NotificationNotiModifyRequest> notiRequest
    ) {
        social.getAdminId();
        var response = notificationBusiness.modifyNoti(notiRequest.getBody());
        return Api.OK(response);
    }

    @Operation(
            summary = "공지사항 활성화 전체 검색"
    )
    @PostMapping("/noti/find")
    public Api<List<NotificationNotiResponse>> findNotis(
            @Valid
            @RequestBody Api<String> notiRequest
    ) {
        var response = notificationBusiness.getNotis();
        return social.withToken(response, notiRequest.getResult());
    }
    @Operation(
            summary = "공지사항 전체 검색"
    )
    @PostMapping("/noti/find/all")
    public Api<NotificationNotiAllResponse> findNotisAll(
            @Valid
            @RequestBody Api<String> notiRequest
    ) {
        var response = notificationBusiness.getNotisAll();
        return Api.OK(response);
    }
}
