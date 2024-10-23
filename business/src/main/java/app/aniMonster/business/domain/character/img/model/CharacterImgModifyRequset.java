package app.aniMonster.business.domain.character.img.model;


import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsActivate;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

import java.util.List;
import java.util.UUID;


@Getter
public class CharacterImgModifyRequset {

    @NotBlank
    public UUID character_id;

    public List<CharacterImgInfo> character_img_info_list;
}
