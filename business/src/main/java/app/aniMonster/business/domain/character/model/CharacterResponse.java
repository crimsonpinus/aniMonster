package app.aniMonster.business.domain.character.model;

import app.aniMonster.business.domain.character.img.model.CharacterImgResponse;
import app.aniMonster.postgresql.db.character.enums.CharacterGender;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterResponse {

    private UUID id;

    private String name;

    private String name_kor;

    private CharacterGender gender;

    private Integer age;

    private String nationality;

    private String personality;

    private CharacterIsActivate is_activate;

    private String description;

    private String prompt;

    private CharacterImgResponse img;
}
