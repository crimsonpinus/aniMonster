package app.aniMonster.postgresql.db.social.entity;

import app.aniMonster.postgresql.db.social.enums.SocialGender;
import app.aniMonster.postgresql.db.social.enums.SocialIsAdult;
import app.aniMonster.postgresql.db.social.enums.SocialStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

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
    private Long id;

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

    @Size(max = 30)
    @Column(name = "nick", length = 30)
    private String nick;

    @Size(max = 50)
    @NotNull
//    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 50)
    private String provider;

    @Size(max = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private SocialStatus status;

    @Size(max = 1)
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "is_adult", nullable = false, length = 20)
    private SocialIsAdult isAdult;

    @NotNull
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Column(name = "unregistered_at")
    private Instant unregisteredAt;



}