package app.aniMonster.business.domain.social.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.social.entity.SocialEntity;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import app.aniMonster.postgresql.db.social.repository.SocialRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocialService {

    private final SocialRepository socialRepository;

    @Transactional
    public SocialEntity sign(SocialEntity socialEntity) {

        var socialCheck = socialRepository.findFirstByEmailAndProviderOrderByEmailDesc(socialEntity.getEmail(), socialEntity.getProvider());

        return socialCheck
                .map(it -> {
                    it.setSocialId(socialEntity.getSocialId());
                    it.setName(socialEntity.getName());
                    return it;
                })
                .orElseGet(() -> {
                    socialEntity.setRegisteredAt(Instant.now());
                    socialEntity.setStatus(SocialStatus.REGISTERED);
                    socialEntity.setIsAdult(SocialIsAdult.UNREGISTERED);
                    return socialRepository.save(socialEntity);
                });
//        return Optional.ofNullable(socialEntity)
//                .map(it -> {
//                    var socialCheck = socialRepository.findFirstByEmailAndProviderOrderByEmailDesc(it.getEmail(), it.getProvider());
//                    if (socialCheck.isPresent()) {
//                        it.setSocialId(socialEntity.getSocialId());
//                    } else {
//                        socialCheck.s
//                        .setRegisteredAt(Instant.now());
//                        socialCheck.setIsAdult("N");
//                    }
//
//                    return socialRepository.save(it);
//                })
//                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT,"Social Entity Null"));
    }

    @Transactional
    public SocialEntity findMe(SocialEntity socialEntity) {
        System.out.println("Social Service findme "+socialEntity.getSocialId());
        return socialRepository.findFirstBySocialIdAndStatusOrderBySocialIdDesc(socialEntity.getSocialId(), SocialStatus.REGISTERED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST,"Not Found Social User"));
    }
}
