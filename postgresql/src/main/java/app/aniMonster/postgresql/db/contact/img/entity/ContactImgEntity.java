package app.aniMonster.postgresql.db.contact.img.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_contact_img")
public class ContactImgEntity {
    @Id
    @Size(max = 100)
    @Column(name = "id", nullable = false, length = 100)
    private String id;

    @NotNull
    @Column(name = "contact_id", nullable = false)
    private Long contactId;

    @Size(max = 300)
    @NotNull
    @Column(name = "url", nullable = false, length = 300)
    private String url;

}