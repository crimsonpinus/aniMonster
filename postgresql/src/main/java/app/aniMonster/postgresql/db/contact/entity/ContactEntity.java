package app.aniMonster.postgresql.db.contact.entity;

import app.aniMonster.postgresql.db.contact.enums.ContactCategory;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_contact")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tbl_contact_id_gen")
    @SequenceGenerator(name = "tbl_contact_id_gen", sequenceName = "tbl_contact_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 150)
    @NotNull
    @Column(name = "social_id", nullable = false, length = 150)
    private String socialId;

    @Size(max = 30)
    @NotNull
    @Column(name = "category", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private ContactCategory category;

    @Size(max = 100)
    @NotNull
    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @NotNull
    @Column(name = "contents", nullable = false, length = Integer.MAX_VALUE)
    private String contents;

    @NotNull
    @Column(name = "registered_at", nullable = false)
    private Instant registeredAt;

    @Size(max = 100)
    @Column(name = "answer_admin_id", length = 100)
    private String adminId;

    @Column(name = "answer", length = Integer.MAX_VALUE)
    private String answer;

    @Column(name = "answer_at")
    private Instant answerAt;

}