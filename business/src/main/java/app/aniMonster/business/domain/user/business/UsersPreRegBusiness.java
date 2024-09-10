package app.aniMonster.business.domain.user.business;

import app.aniMonster.business.common.annotation.Business;
import app.aniMonster.business.domain.user.converter.UsersPreRegConvertor;
import app.aniMonster.business.domain.user.model.UsersPreRegRequest;
import app.aniMonster.business.domain.user.model.UsersPreRegResponse;
import app.aniMonster.business.domain.user.service.UsersPreRegService;
import app.aniMonster.postgresql.db.user.entity.UsersPreReg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Business
@Slf4j
public class UsersPreRegBusiness {

    private final UsersPreRegService usersPreRegService;
    private final UsersPreRegConvertor usersPreRegConvertor;

    public UsersPreRegResponse register(UsersPreRegRequest request) {

        var entity = usersPreRegConvertor.toEntity(request);
        var newEntity = usersPreRegService.register(entity);
        var response = usersPreRegConvertor.toResponse(newEntity);
        return response;
    }

    public UsersPreRegResponse findByMobile(String mobile) {
        var entity = usersPreRegConvertor.toEntity(mobile);
        Optional<UsersPreReg> newEntity = usersPreRegService.findByMobile(entity);
        var response = usersPreRegConvertor.toResponse(newEntity.orElse(null));
        return response;
    }
}
