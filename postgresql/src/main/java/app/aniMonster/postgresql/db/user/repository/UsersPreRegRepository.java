package app.aniMonster.postgresql.db.user.repository;

import app.aniMonster.postgresql.db.user.entity.UsersPreReg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersPreRegRepository extends JpaRepository<UsersPreReg, Long> {

    Optional<UsersPreReg> findFirstByMobileOrderByIdDesc(String mobile);
}
