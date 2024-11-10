package app.aniMonster.business.domain.character.img.model;

import app.aniMonster.postgresql.db.character.img.enums.CharacterImgCategory;
import app.aniMonster.postgresql.db.character.img.enums.CharacterImgIsSelected;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterImgSingleResponse {

    public String id;
    public CharacterImgCategory category;
    public String url;
    public CharacterImgIsSelected is_selected;
}
