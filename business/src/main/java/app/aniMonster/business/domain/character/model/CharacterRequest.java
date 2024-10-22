package app.aniMonster.business.domain.character.model;

import app.aniMonster.postgresql.db.character.enums.CharacterGender;
import app.aniMonster.postgresql.db.character.enums.CharacterIsActivate;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CharacterRequest {

    @NotBlank
    public String name;

    @NotBlank
    public String name_kor;

    @NotBlank
    public CharacterGender gender;

    @NotBlank
    public Integer age;

    public String nationality;

    public String personality;

    @NotBlank
    public String description;

    @NotBlank
    public String prompt;


}
