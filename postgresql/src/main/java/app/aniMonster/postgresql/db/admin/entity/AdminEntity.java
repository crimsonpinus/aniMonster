package app.aniMonster.postgresql.db.admin.entity;

import app.aniMonster.postgresql.db.admin.enums.AdminAuthor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_admin")
public class AdminEntity {
    @Id
    @ColumnDefault("uuid_generate_v4()")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Size(max = 20)
    @NotNull
    @Column(name = "admin_id", nullable = false, length = 20)
    private String adminId;

    @Size(max = 50)
    @NotNull
    @Column(name = "admin_password", nullable = false, length = 50)
    private String adminPassword;

    @Size(max = 20)
    @NotNull
    @Column(name = "admin_author", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private AdminAuthor adminAuthor;

    @ColumnDefault("now()")
    @Column(name = "registered_at")
    private Instant registeredAt;

}