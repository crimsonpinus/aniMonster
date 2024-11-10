package app.aniMonster.postgresql.db.character.img.entity;

import app.aniMonster.postgresql.db.character.img.enums.CharacterImgCategory;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsSelected;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_character_img")
public class CharacterImgEntity {
    @Id
    @Size(max = 100)
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @NotNull
    @Column(name = "character_id", nullable = false)
    private UUID characterId;

    @Size(max = 30)
    @NotNull
    @Column(name = "category", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private CharacterImgCategory category;

    @Size(max = 10)
    @ColumnDefault("'ACTIVATED'")
    @Column(name = "is_activate", length = 10)
    @Enumerated(EnumType.STRING)
    private CharacterImgIsActivate isActivate;

    @Size(max = 300)
    @NotNull
    @Column(name = "url", nullable = false, length = 300)
    private String url;

    @Size(max = 20)
    @Column(name = "is_selected", length = 20)
    @Enumerated(EnumType.STRING)
    private CharacterImgIsSelected isSelected;

}