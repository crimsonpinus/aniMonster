package app.aniMonster.business.domain.file.model;

import app.aniMonster.postgresql.db.file.enums.FileCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileRequest {

    @NotBlank
    private String upload_id;

    @NotBlank
    private FileCategory category;

    private String info;

}
