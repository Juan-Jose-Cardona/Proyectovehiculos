package co.edu.usbcali.vehiculosnotificacion.model;

import co.edu.usbcali.vehiculosnotificacion.model.enums.ChannelType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obligation_rule_channels")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObligationRuleChannel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //relacion obligation rule
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "obligation_rule_id", nullable = false)
    private ObligationRule obligationRule;

    //canal notificacion
    @Enumerated(EnumType.STRING)
    @Column(name = "channel", nullable = false)
    private ChannelType channel;

}