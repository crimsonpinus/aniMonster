package app.aniMonster.business.domain.notification.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationFaqAllResponse {

    private List<NotificationFaqResponse> activate;
    private List<NotificationFaqResponse> deactivate;
}
