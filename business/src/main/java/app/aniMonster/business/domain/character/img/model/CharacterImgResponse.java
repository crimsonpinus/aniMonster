package app.aniMonster.business.domain.character.img.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterImgResponse {
    public CharacterImgSingleResponse thumbnail_img;
    public List<CharacterImgSingleResponse> character_img;
    public List<CharacterImgSingleResponse> background_img;
    public List<CharacterImgSingleResponse> album_img;

}
