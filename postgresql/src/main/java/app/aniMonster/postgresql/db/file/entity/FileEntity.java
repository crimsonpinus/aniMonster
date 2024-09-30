package app.aniMonster.postgresql.db.file.entity;

import app.aniMonster.postgresql.db.file.enums.FileCategory;
import app.aniMonster.postgresql.db.file.enums.FileIsActivate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "tbl_file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_file_id_gen")
    @SequenceGenerator(name = "tbl_file_id_gen", sequenceName = "tbl_file_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 100)
    @NotNull
    @Column(name = "file_name", nullable = false, length = 100)
    private String fileName;

    @Size(max = 10)
    @NotNull
    @Column(name = "file_ext", nullable = false, length = 10)
    private String fileExt;

    @NotNull
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Size(max = 30)
    @NotNull
    @Column(name = "category", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private FileCategory category;

    @Size(max = 100)
    @NotNull
    @Column(name = "file_id", nullable = false, length = 100)
    private String fileId;

    @Size(max = 150)
    @NotNull
    @Column(name = "file_path", nullable = false, length = 150)
    private String filePath;

    @Size(max = 10)
    @ColumnDefault("'ACTIVATED'")
    @Column(name = "is_activate", length = 10)
    @Enumerated(EnumType.STRING)
    private FileIsActivate isActivate;

    @Column(name = "deactivated_at")
    private Instant deactivatedAt;

}