package app.aniMonster.business.domain.user.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.user.entity.UsersPreReg;
import app.aniMonster.postgresql.db.user.repository.UsersPreRegRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UsersPreRegService {

    private final UsersPreRegRepository usersPreRegRepository;

    public UsersPreReg register(UsersPreReg usersPreReg) {
        return Optional.ofNullable(usersPreReg)
                .map(it -> {
                    it.setMobile(usersPreReg.getMobile());
                    return usersPreRegRepository.save(it);

                })
                .orElseThrow(() ->  new BusinessException(BusinessErrorCode.NULL_POINT, "UserPreReg is null"));


    }

    public Optional<UsersPreReg> findByMobile(String mobile) {
        return Optional.ofNullable(mobile)
                .map(it -> {
                    return usersPreRegRepository.findFirstByMobileOrderByIdDesc(mobile);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST, "사전 등록한 유저"));
    }
}
