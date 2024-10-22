package app.aniMonster.business.domain.character.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CharacterNameRequest {

    @NotBlank
    public String name;
}
