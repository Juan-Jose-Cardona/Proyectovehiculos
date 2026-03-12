package co.edu.usbcali.vehiculosnotificacion.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(
        name = "users",
        indexes = {
                @Index(name = "idx_users_phone", columnList = "phone")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="email", nullable = false, unique = true, length = 255)
    private String email;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "password", nullable = false, unique = true, columnDefinition = "text")
    private String password;

    @Column(name = "full_name", length = 255)
    private String fullName;

    // DDL: NOT NULL, default 'America/Bogota', NO UNIQUE
    @Column(name = "timezone", nullable = false, length = 100)
    private String timezone;

    // DDL: NOT NULL, default true
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    // DDL: timestamptz NOT NULL default now()
    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp updatedAt;

    @PrePersist
    void prePersist() {
        if (timezone == null) timezone = "America/Bogota";
        if (isActive == null) isActive = true;

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
