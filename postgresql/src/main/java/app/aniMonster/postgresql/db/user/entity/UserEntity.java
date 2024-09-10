package app.aniMonster.postgresql.db.user.entity;

import app.aniMonster.postgresql.db.user.enums.user.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_test_id_gen")
    @SequenceGenerator(name = "users_test_id_gen", sequenceName = "users_test_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 50)
    @NotNull
    @Column(name = "password", nullable = false, length = 50)
    private String password;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Size(max = 150)
    @NotNull
    @Column(name = "address", nullable = false, length = 150)
    private String address;

    @ColumnDefault("now()")
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Column(name = "unregisterde_at")
    private Instant unregisterdeAt;

    @Column(name = "last_login")
    private Instant lastLogin;

}