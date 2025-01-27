package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "atto")
@NamedQuery(name = "Atto.findAll", query = "SELECT a FROM Atto a")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_atto")
public class Atto extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "tipo_atto")
    public TipoAtto tipoAtto;

    @Column(name = "numero_atto")
    @Size(max = 255)
    public String numeroAtto;  //numero

    @Temporal(TemporalType.DATE)
    @Column(name = "data_atto")
    public Date dataAtto;     //data
}
