package co.edu.usbcali.vehiculosnotificacion.model;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalTime;

@Entity
@Table(
        name = "obligation_rules",
        indexes = {
                @Index(name = "idx_obligation_rules_enabled", columnList = "is_enabled")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObligationRule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // obligation_id integer NOT NULL UNIQUE references obligations on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obligation_id", nullable = false, unique = true)
    private Obligation obligation;

    // integer[] NOT NULL default {30,7,1}
    @Column(name = "notify_days", nullable = false, columnDefinition = "integer[]")
    private Integer[] notifyDays;

    // channel_type[] NOT NULL default {EMAIL}
    @Enumerated(EnumType.STRING)
    @Column(name = "channels", nullable = false, columnDefinition = "channel_type[]")
    private ChannelType[] channels;

    // time NOT NULL default 08:00:00
    @Column(name = "send_window_start", nullable = false)
    private LocalTime sendWindowStart;

    @Column(name = "send_window_end", nullable = false)
    private LocalTime sendWindowEnd;

    // boolean default true NOT NULL
    @Column(name = "is_enabled", nullable = false)
    private Boolean isEnabled;

    @Column(name = "created_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz")
    private Timestamp updatedAt;

    @PrePersist
    void prePersist() {

        if (notifyDays == null) notifyDays = new Integer[]{30,7,1};
        if (channels == null) channels = new ChannelType[]{ChannelType.EMAIL};
        if (sendWindowStart == null) sendWindowStart = LocalTime.of(8,0);
        if (sendWindowEnd == null) sendWindowEnd = LocalTime.of(18,0);
        if (isEnabled == null) isEnabled = true;

        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
