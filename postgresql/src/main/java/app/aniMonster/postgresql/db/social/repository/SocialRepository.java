package app.aniMonster.postgresql.db.social.repository;

import app.aniMonster.postgresql.db.social.entity.SocialEntity;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialRepository extends JpaRepository<SocialEntity, Long> {

    Optional<SocialEntity> findFirstByEmailAndProviderOrderByEmailDesc(String email, String provider);
    Optional<SocialEntity> findFirstBySocialIdAndStatusOrderByIdDesc(String socialId, SocialStatus status);
    Optional<SocialEntity> findFirstBySocialIdOrderByIdDesc(String socialId);
}
