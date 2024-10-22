package app.aniMonster.postgresql.db.character.entity;

import app.aniMonster.postgresql.db.character.enums.CharacterGender;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_character")
public class CharacterEntity {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Size(max = 50)
    @NotNull
    @Column(name = "name_kor", nullable = false, length = 50)
    private String nameKor;

    @Size(max = 20)
    @NotNull
    @Column(name = "gender", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CharacterGender gender;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 50)
    @Column(name = "nationality", length = 50)
    private String nationality;

    @Size(max = 50)
    @Column(name = "personality", length = 50)
    private String personality;

    @Size(max = 10)
    @ColumnDefault("'ACTIVATED'")
    @Column(name = "is_activate", length = 10)
    @Enumerated(EnumType.STRING)
    private CharacterIsActivate isActivate;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Size(max = 150)
    @Column(name = "creation_id", length = 150)
    private String creationId;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @NotNull
    @Column(name = "prompt", nullable = false, length = Integer.MAX_VALUE)
    private String prompt;

}