package app.aniMonster.business.domain.file.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class FileResponse {

    private String url;

    private String file_name;

    private String file_ext;

    private String file_path;

    private String file_id;

}
