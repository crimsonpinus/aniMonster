package app.aniMonster.postgresql.db.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
@Slf4j
public class UserRepositoryImpl implements UserRepositoryCustom {


    private final JPAQueryFactory query;

;





//    @Transactional
//    @Override
//    public UserTest signUp(UserTest user) {
//        log.info(password);
//        String signUpQuery =
//                "INSERT INTO UserTest " +
//                "(name, email, password, address, status)" +
//                        "VALUES (" +
//                        encrypt(":name") + ", " +
//                        encrypt(":email") + ", " +
//                        encrypt(":password") +
//                        ", :address, :status)";
////                        ":name, :email, :password , :address, :status)";
//        em.createQuery(signUpQuery)
//                .setParameter("name",user.getName())
//                .setParameter("email",user.getEmail())
//                .setParameter("password",user.getPassword())
//                .setParameter("address",user.getAddress())
//                .setParameter("status", user.getStatus())
//                .executeUpdate();
//        em.flush();
//        em.clear();
//        log.info(user.getName());
//        log.info("Sign up UserTest=======================================");
//        log.info(encrypt(user.getName()));
//        QUserTest ut = QUserTest.userTest;
//        long result = query.insert(ut)
//                .columns(ut.name, ut.email, ut.password, ut.address, ut.status)
//                .values(
////                        user.getName(),
////                        user.getEmail(),
////                        user.getPassword(),
//                        encrypt(user.getName()),
//                        encrypt(user.getEmail()),
//                        encrypt(user.getPassword()),
//                        user.getAddress(),
//                        user.getStatus()
//                )
//                .execute();
//        query.insert(ut)
//                .columns(ut.name, ut.email, ut.password, ut.address, ut.status)
//                .values(user.getName(), user.getEmail(), user.getPassword(), user.getAddress(), user.getStatus())
//                .execute();
//        return user;
//    }


}
