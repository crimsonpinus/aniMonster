package app.aniMonster.api.controller.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Objects;


@RequiredArgsConstructor
@Component
public class Admin {


    public String getAdminId() {
        var requestContext = Objects.requireNonNull(
                RequestContextHolder.getRequestAttributes()
        );
        var socialId = requestContext.getAttribute("adminId", RequestAttributes.SCOPE_REQUEST);
        socialId = Objects.requireNonNull(socialId);
        return socialId.toString();
    }


}