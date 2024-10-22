package app.aniMonster.business.domain.character.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.UUID;

@Getter
public class CharacterIdRequest {

    @NotBlank
    public UUID id;
}
