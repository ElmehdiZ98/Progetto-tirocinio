package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.math.BigDecimal;


@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "quadro_economico")
@NamedQuery(name = "QuadroEconomico.findAll", query = "SELECT qe FROM QuadroEconomico qe")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable("history_quadro_economico")
public class QuadroEconomico extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "progetto_id", referencedColumnName = "id")
    @NotNull(message = "Il progetto è obbligatorio")
    private Progetto progetto;

    @Column(name = "importo_totale", precision = 15, scale = 2)
    @Min(0)
    private BigDecimal importoTotale = BigDecimal.ZERO;
}
