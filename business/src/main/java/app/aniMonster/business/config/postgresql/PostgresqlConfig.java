package app.aniMonster.business.config.postgresql;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;import jakarta.persistence.PersistenceContext;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@NoArgsConstructor
@Configuration
@EntityScan(basePackages = "app.aniMonster.postgresql")
@EnableJpaRepositories(basePackages = "app.aniMonster.postgresql")
public class PostgresqlConfig {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(this.entityManager);
    }
}
