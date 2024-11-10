package app.aniMonster.business.domain.character.img.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterImgRequest {

    @NotBlank
    public MultipartFile thumbnail;

    @NotBlank
    public List<MultipartFile> character;

    @NotBlank
    public List<MultipartFile> background;

    @NotBlank
    public List<MultipartFile> album;
}
