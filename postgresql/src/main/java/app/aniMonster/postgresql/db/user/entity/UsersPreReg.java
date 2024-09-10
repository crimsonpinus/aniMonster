package app.aniMonster.postgresql.db.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "users_pre_reg")
public class UsersPreReg {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_pre_reg_id_gen")
    @SequenceGenerator(name = "users_pre_reg_id_gen", sequenceName = "users_pre_reg_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 70)
    @NotNull
    @Column(name = "mobile", nullable = false, length = 70)
    private String mobile;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "personal_info_collention", nullable = false)
    private Boolean personalInfoCollention = false;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "receive_event", nullable = false)
    private Boolean receiveEvent = false;

    @NotNull
    @ColumnDefault("true")
    @Column(name = "older_14years", nullable = false)
    private Boolean older14years = false;

    @NotNull
    @ColumnDefault("now()")
    @Column(name = "reg_date", nullable = false)
    private Instant regDate;

}