package app.aniMonster.postgresql.db.social.entity;

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
@Table(name = "social")
public class SocialEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "social_id_gen")
    @SequenceGenerator(name = "social_id_gen", sequenceName = "social_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 150)
    @NotNull
    @Column(name = "social_id", nullable = false, length = 150)
    private String socialId;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @Size(max = 50)
    @NotNull
    @Column(name = "provider", nullable = false, length = 50)
    private String provider;

    @Size(max = 50)
    @NotNull
    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Size(max = 1)
    @NotNull
    @ColumnDefault("'N'")
    @Column(name = "is_adult", nullable = false, length = 1)
    private String isAdult;

    @NotNull
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Column(name = "unregistered_at")
    private Instant unregisteredAt;

}