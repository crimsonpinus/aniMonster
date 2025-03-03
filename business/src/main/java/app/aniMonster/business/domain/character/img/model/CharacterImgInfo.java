package app.aniMonster.business.domain.character.img.model;

import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsSelected;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CharacterImgInfo {
    @NotBlank
    public String id;
    @NotBlank
    public CharacterImgIsActivate is_activate;

    public CharacterImgIsSelected is_selected;
}
