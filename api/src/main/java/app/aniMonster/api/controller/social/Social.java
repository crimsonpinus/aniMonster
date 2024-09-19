package app.aniMonster.api.controller.social;

import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;



@Component
public class Social {

    public String getSocialId() {
        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );
        var socialId = requestContext.getAttribute("socialId", RequestAttributes.SCOPE_REQUEST);
        socialId = Objects.requireNonNull(socialId);
        return socialId.toString();
    }
}
