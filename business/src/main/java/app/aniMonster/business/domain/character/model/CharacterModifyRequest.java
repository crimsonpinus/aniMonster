package app.aniMonster.business.domain.character.model;

import app.aniMonster.postgresql.db.character.enums.CharacterGender;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterModifyRequest {

    public UUID id;

    public String name;

    public String name_kor;

    public CharacterGender gender;

    public Integer age;

    public String nationality;

    public String personality;

    public CharacterIsActivate is_activate;

    public String description;

    public String prompt;


}
