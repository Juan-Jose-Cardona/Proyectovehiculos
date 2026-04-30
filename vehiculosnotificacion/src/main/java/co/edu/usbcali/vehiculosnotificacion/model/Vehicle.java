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
        name = "vehicles",
        uniqueConstraints = {
                @UniqueConstraint(name = "uq_vehicles_user_plate", columnNames = {"user_id", "plate"})
        },
        indexes = {
                @Index(name = "idx_vehicles_user", columnList = "user_id")
        }
)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // serial
    private Integer id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false, referencedColumnName = "id")
    private User user;

    @Column(name = "plate", nullable = false, length = 20)
    private String plate;

    @Column(name = "brand", length = 100)
    private String brand;

    @Column(name = "line_model", length = 100)
    private String lineModel;

    @Column(name = "model_year")
    private Integer modelYear;

    @Column(name = "vehicle_type", nullable = false, length = 50)
    private String vehicleType;

    @Column(name = "notes", columnDefinition = "text")
    private String notes;

    // DDL: timestamptz default now() NOT NULL
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;

    @PrePersist
    void prePersist() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        if (createdAt == null) createdAt = now;
        if (updatedAt == null) updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = new Timestamp(System.currentTimeMillis());
    }

}
