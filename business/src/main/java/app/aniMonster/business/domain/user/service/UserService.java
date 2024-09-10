package app.aniMonster.business.domain.user.service;


import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.error.UserBusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.user.entity.UserEntity;
import app.aniMonster.postgresql.db.user.enums.user.UserStatus;
import app.aniMonster.postgresql.db.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;


    public UserEntity register(UserEntity user){
        return Optional.ofNullable(user)
                .map(it -> {
                    it.setStatus(UserStatus.REGISTERED);
                    it.setRegisteredAt(Instant.now());
                    return userRepository.save(it);
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "User Entity Null"));
    }

    public UserEntity logIn(UserEntity user) {
        return userRepository
                .findFirstByEmailAndPasswordAndStatusOrderByIdDesc(
                        user.getEmail(),
                        user.getPassword(),
                        user.getStatus()
                ).orElseThrow(() -> new BusinessException(UserBusinessErrorCode.USER_NOT_FOUND, "Login Error User Not Found" ));

    }

    public UserEntity getUserWithId(Long userId) {
        return  userRepository
                .findFirstByIdAndStatusOrderByIdDesc(
                        userId,
                        UserStatus.REGISTERED
                ).orElseThrow(() -> new BusinessException(UserBusinessErrorCode.USER_NOT_FOUND, "User Not Found" ));

    }
}
