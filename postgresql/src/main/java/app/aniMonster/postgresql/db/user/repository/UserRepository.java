package app.aniMonster.postgresql.db.user.repository;

import app.aniMonster.postgresql.db.user.entity.UserEntity;
import app.aniMonster.postgresql.db.user.enums.user.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer>  {


    Optional<UserEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, UserStatus status);

    Optional<UserEntity> findFirstByEmailAndPasswordAndStatusOrderByIdDesc(String email, String password, UserStatus status);


}
