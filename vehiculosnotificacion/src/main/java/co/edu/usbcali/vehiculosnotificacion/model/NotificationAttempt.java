package co.edu.usbcali.vehiculosnotificacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(
        name = "notification_attempts",
        indexes = {
                @Index(name = "idx_notification_attempts_notification", columnList = "notification_id")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationAttempt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // bigserial
    private Long id;

    // notification_id NOT NULL references notifications on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notification_id", nullable = false, referencedColumnName = "id")
    private Notification notification;

    @Column(name = "attempt_no", nullable = false)
    private Integer attemptNo;

    @Column(name = "provider", length = 100)
    private String provider;

    @Column(name = "request_meta", columnDefinition = "jsonb")
    private String requestMeta;

    @Column(name = "response_meta", columnDefinition = "jsonb")
    private String responseMeta;

    @Column(name = "success", nullable = false)
    private Boolean success;

    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp createdAt;

    @PrePersist
    void prePersist() {
        if (createdAt == null) {
            createdAt = new Timestamp(System.currentTimeMillis());
        }
    }

}
