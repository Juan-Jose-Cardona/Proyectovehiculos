package co.edu.usbcali.vehiculosnotificacion.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obligation_rule_notify_days")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObligationRuleNotifyDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //relacion obligation rule
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obligation_rule_id", nullable = false)
    private ObligationRule obligationRule;

    //dia de notificacion
    @Column(name = "notify_day", nullable = false)
    private Integer notifyDay;

}