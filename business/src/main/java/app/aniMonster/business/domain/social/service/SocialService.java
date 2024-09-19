package app.aniMonster.business.domain.social.service;

import app.aniMonster.business.common.error.BusinessErrorCode;
import app.aniMonster.business.common.exception.BusinessException;
import app.aniMonster.postgresql.db.social.entity.SocialEntity;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import app.aniMonster.postgresql.db.social.profile.enums.SocialProfileGender;
import app.aniMonster.postgresql.db.social.repository.SocialRepository;
import app.aniMonster.postgresql.db.social.profile.entity.SocialProfileEntity;
import app.aniMonster.postgresql.db.social.profile.repository.SocialProfileRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
@Slf4j
public class SocialService {

    private final SocialRepository socialRepository;
    private final SocialProfileRepository socialProfileRepository;

    @Transactional
    public SocialEntity sign(SocialEntity socialEntity) {

        var socialCheck = socialRepository.findFirstByEmailAndProviderOrderByEmailDesc(socialEntity.getEmail(), socialEntity.getProvider());

        return socialCheck
                .map(it -> {
                    it.setSocialId(socialEntity.getSocialId());
                    it.setName(socialEntity.getName());
                    it.setStatus(SocialStatus.REGISTERED);
                    return it;
                })
                .orElseGet(() -> {
                    socialEntity.setRegisteredAt(Instant.now());
                    socialEntity.setStatus(SocialStatus.REGISTERED);
                    socialEntity.setIsAdult(SocialIsAdult.UNREGISTERED);

                    socialProfileRepository.save(SocialProfileEntity.builder()
                            .socialId(socialEntity.getSocialId())
                            .gender(SocialProfileGender.NOT_SET)
                            .build()
                    );

                    return socialRepository.save(socialEntity);
                });
    }


    @Transactional
    public SocialEntity update(SocialEntity socialEntity) {
        var socialCheck = socialRepository.findFirstBySocialIdOrderByIdDesc(socialEntity.getSocialId());

        return socialCheck
                .map(it -> {
                    it.setNick(socialEntity.getNick());

                    if (socialEntity.getStatus() != null) {
                        it.setStatus(socialEntity.getStatus());
                    }
                    if (socialEntity.getIsAdult() != null){
                        it.setIsAdult(socialEntity.getIsAdult());
                    }
                    if (socialEntity.getStatus() == SocialStatus.UNREGISTERED) {
                        it.setUnregisteredAt(Instant.now());
                    }
                    log.info("Updating social profile with id {}", socialEntity.getSocialId());
                    return it;
                })
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.NULL_POINT, "Not Found Social User"));

    }

    @Transactional
    public SocialEntity showMe(SocialEntity socialEntity) {
        return socialRepository.findFirstBySocialIdAndStatusOrderByIdDesc(socialEntity.getSocialId(), SocialStatus.REGISTERED)
                .orElseThrow(() -> new BusinessException(BusinessErrorCode.BAD_REQUEST,"Not Found Social User"));
    }


}
