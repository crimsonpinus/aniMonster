package app.aniMonster.business.domain.character.img.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacterImgIdResponse {
    public CharacterImgResponse activate;
    public CharacterImgResponseAll deactivate;
}
