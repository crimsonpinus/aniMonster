package app.aniMonster.api.controller.contact;

import app.aniMonster.api.common.api.Api;
import app.aniMonster.business.domain.contact.business.ContactBusiness;
import app.aniMonster.business.domain.contact.model.ContactConsumerRequest;
import app.aniMonster.business.domain.contact.model.ContactResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/contact")
public class ContactApiController {

    private final ContactBusiness contactBusiness;

    @PostMapping("/save")
    public Api<ContactResponse> faq(
            @Valid
            @RequestBody Api<ContactConsumerRequest> request
    ) {
        var response = contactBusiness.save(request.getBody());
        return Api.OK(response);
    }
}
