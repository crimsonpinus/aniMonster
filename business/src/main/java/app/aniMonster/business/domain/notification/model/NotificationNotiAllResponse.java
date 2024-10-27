package app.aniMonster.business.domain.notification.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationNotiAllResponse {

    private List<NotificationNotiResponse> activate;
    private List<NotificationNotiResponse> deactivate;
}
