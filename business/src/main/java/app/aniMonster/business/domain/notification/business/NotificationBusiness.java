package app.aniMonster.business.domain.notification.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.notification.converter.NotificationConverter;
import app.aniMonster.business.domain.notification.model.*;
import app.aniMonster.business.domain.notification.service.NotificationService;
import app.aniMonster.postgresql.db.notification.entity.NotificationEntity;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class NotificationBusiness {

    private final NotificationConverter notificationConverter;
    private final NotificationService notificationService;

    /**
     * FAQ Start
     * @param faqRequest
     * @return
     */
    public NotificationFaqResponse saveFaq(NotificationFaqRequest faqRequest) {
        var entity = notificationConverter.toEntity(faqRequest);
        var usedEntity = notificationService.save(entity);
        var response = notificationConverter.toFaqResponse(usedEntity);
        return response;
    }

    public NotificationFaqResponse modifyFaq(NotificationFaqModifyRequest faqRequest) {
        var entity = notificationConverter.toEntity(faqRequest);
        var usedEntity = notificationService.modifyFaq(entity);
        var response = notificationConverter.toFaqResponse(usedEntity);
        return response;
    }

    public List<NotificationFaqResponse> getFaqs() {
        var usedEntity = notificationService.getFaqs();
        return toListOfFaqs(usedEntity);
    }

    public NotificationFaqAllResponse getFaqsAll() {
        var usedEntity = notificationService.getFaqsAll();
        var activateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate() == NotificationIsActivate.ACTIVATED)
                .toList();
        var deactivateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate() == NotificationIsActivate.DEACTIVATED)
                .toList();
        var activateListFaq = toListOfFaqs(activateEntity);
        var deactivateListFaq = toListOfFaqs(deactivateEntity);

        return NotificationFaqAllResponse.builder()
                .activate(activateListFaq)
                .deactivate(deactivateListFaq)
                .build();
    }// FAQ End



    /**
     * NOTI Start
     * @param notiRequest
     * @return
     */
    public NotificationNotiResponse saveNoti(NotificationNotiRequest notiRequest) {
        var entity = notificationConverter.toEntity(notiRequest);
        var usedEntity = notificationService.save(entity);
        var response = notificationConverter.toNotiResponse(usedEntity);
        return response;
    }

    public NotificationNotiResponse modifyNoti(NotificationNotiModifyRequest notiRequest) {
        var entity = notificationConverter.toEntity(notiRequest);
        var usedEntity = notificationService.modifyNoti(entity);
        System.out.println("=====================" + usedEntity);
        var response = notificationConverter.toNotiResponse(usedEntity);
        return response;
    }

    public List<NotificationNotiResponse> getNotis() {
        var usedEntity = notificationService.getNotis();
        return toListOfNotis(usedEntity);
    }

    public NotificationNotiAllResponse getNotisAll() {
        var usedEntity = notificationService.getNotisAll();
        var activateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate() == NotificationIsActivate.ACTIVATED)
                .toList();
        var deactivateEntity = usedEntity.stream()
                .filter(it -> it.getIsActivate() == NotificationIsActivate.DEACTIVATED)
                .toList();
        var activateListNoti = toListOfNotis(activateEntity);
        var deactivateListNoti = toListOfNotis(deactivateEntity);

        return NotificationNotiAllResponse.builder()
                .activate(activateListNoti)
                .deactivate(deactivateListNoti)
                .build();
    }//noti end






    /**
     *
     * @param notificationEntities
     * @return
     */
    private List<NotificationFaqResponse> toListOfFaqs(List<NotificationEntity> notificationEntities) {
        var faqList = new ArrayList<NotificationFaqResponse>();
        notificationEntities.forEach(entity -> faqList.add(notificationConverter.toFaqResponse(entity)));
        return faqList;
    }

    private List<NotificationNotiResponse> toListOfNotis(List<NotificationEntity> notificationEntities) {
        var notiList = new ArrayList<NotificationNotiResponse>();

        notificationEntities.forEach(entity -> notiList.add(notificationConverter.toNotiResponse(entity)));
        return notiList;
    }
}
