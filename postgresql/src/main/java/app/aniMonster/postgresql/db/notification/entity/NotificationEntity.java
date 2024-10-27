package app.aniMonster.postgresql.db.notification.entity;

import app.aniMonster.postgresql.db.notification.enums.NotificationCategory;
import app.aniMonster.postgresql.db.notification.enums.NotificationIsActivate;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_notification_id_gen")
    @SequenceGenerator(name = "tbl_notification_id_gen", sequenceName = "tbl_notification_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "contents", nullable = false, length = Integer.MAX_VALUE)
    private String contents;

    @NotNull
    @Column(name = "explanation", length = Integer.MAX_VALUE)
    private String explanation;

    @ColumnDefault("1000")
    @Column(name = "priority")
    private Integer priority;

    @Size(max = 20)
    @NotNull
    @Column(name = "category", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private NotificationCategory category;

    @Size(max = 15)
    @ColumnDefault("'ACTIVATED'")
    @Column(name = "is_activate", length = 15)
    @Enumerated(EnumType.STRING)
    private NotificationIsActivate isActivate;

    @Column(name = "limit_at")
    private Instant limitAt;

    @ColumnDefault("now()")
    @Column(name = "registered_at")
    private Instant registeredAt;

}