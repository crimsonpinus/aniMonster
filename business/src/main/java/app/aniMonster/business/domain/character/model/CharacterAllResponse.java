package app.aniMonster.business.domain.character.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CharacterAllResponse {

    private List<CharacterResponse> activate;
    private List<CharacterResponse> deactivate;
}
