package app.aniMonster.postgresql.db.social.profile.repository;

import app.aniMonster.postgresql.db.social.profile.entity.SocialProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SocialProfileRepository extends JpaRepository<SocialProfileEntity, Long> {

    Optional<SocialProfileEntity> findFirstBySocialIdOrderByIdDesc(String socialId);
}
