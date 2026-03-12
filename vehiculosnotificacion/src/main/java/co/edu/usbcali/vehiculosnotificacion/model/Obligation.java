package co.edu.usbcali.vehiculosnotificacion.model;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationStatus;
import co.edu.usbcali.vehiculosnotificacion.model.enums.ObligationType;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Entity
@Table(
        name = "obligations",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_obligations_vehicle_type", columnNames = {"vehicle_id", "type"})
        },
        indexes = {
                @Index(name = "idx_obligations_vehicle", columnList = "vehicle_id"),
                @Index(name = "idx_obligations_due_date", columnList = "due_date"),
                @Index(name = "idx_obligations_status", columnList = "status")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Obligation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    // vehicle_id integer NOT NULL references vehicles on delete cascade
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vehicle_id", nullable = false, referencedColumnName = "id")
    private Vehicle vehicle;

    // type obligation_type NOT NULL
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private ObligationType type;

    // due_date date NOT NULL
    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    // status obligation_status default 'VIGENTE' NOT NULL
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ObligationStatus status;

    // last_calc_at timestamptz nullable
    @Column(name = "last_calc_at")
    private Timestamp lastCalcAt;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    // created_at/updated_at timestamptz default now() NOT NULL
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    void prePersist() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
        if (status == null) status = ObligationStatus.VIGENTE; // default DB
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
