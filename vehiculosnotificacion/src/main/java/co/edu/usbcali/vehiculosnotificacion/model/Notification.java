package co.edu.usbcali.vehiculosnotificacion.model;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationKind;
import co.edu.usbcali.vehiculosnotificacion.model.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(
        name = "notifications",
        // Aproximación del unique index de idempotencia (sin el COALESCE)
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uq_notifications_idempotency",
                        columnNames = {"obligation_id", "channel", "due_date", "days_before_due"}
                )
        },
        indexes = {
                @Index(name = "idx_notifications_status_sched", columnList = "status, scheduled_for"),
                @Index(name = "idx_notifications_locked", columnList = "locked_at"),
                @Index(name = "idx_notifications_obligation", columnList = "obligation_id")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // user_id NOT NULL references users on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, referencedColumnName = "id")
    private User user;

    // vehicle_id NOT NULL references vehicles on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false, referencedColumnName = "id")
    private Vehicle vehicle;

    // obligation_id NOT NULL references obligations on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obligation_id", nullable = false, referencedColumnName = "id")
    private Obligation obligation;

    // channel channel_type NOT NULL
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false, length = 50)
    private ChannelType channel;

    // kind notification_kind default 'DUE_IN_DAYS' NOT NULL
    @Enumerated(EnumType.STRING)
    @Column(name = "kind", nullable = false, length = 50)
    private NotificationKind kind;

    @Column(name = "days_before_due")
    private Integer daysBeforeDue;

    // due_date date NOT NULL
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    // scheduled_for timestamptz NOT NULL
    @Column(name = "scheduled_for", nullable = false, columnDefinition = "timestamptz")
    private Timestamp scheduledFor;

    // payload_json jsonb
    @Column(name = "payload_json", columnDefinition = "text")
    private String payloadJson;

    // status notification_status default 'PENDING' NOT NULL
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private NotificationStatus status;

    // attempt_count integer default 0 NOT NULL
    @Column(name = "attempt_count", nullable = false)
    private Integer attemptCount;

    @Column(name = "last_error", columnDefinition = "text")
    private String lastError;

    @Column(name = "locked_by", length = 100)
    private String lockedBy;

    @Column(name = "locked_at", columnDefinition = "timestamptz")
    private Timestamp lockedAt;

    @Column(name = "sent_at", columnDefinition = "timestamptz")
    private Timestamp sentAt;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp updatedAt;

    @PrePersist
    void prePersist() {
        if (kind == null) kind = NotificationKind.DUE_IN_DAYS;
        if (status == null) status = NotificationStatus.PENDING;
        if (attemptCount == null) attemptCount = 0;

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
