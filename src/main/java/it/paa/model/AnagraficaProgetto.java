package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "anagrafica_progetto")
@NamedQuery(name = "AnagraficaProgetto.findAll", query = "SELECT a FROM AnagraficaProgetto a")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_anagrafica_progetto")
public class AnagraficaProgetto extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "progetto_id")
    public Progetto progetto;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_avvio")
    public Date dataAvvio;


}
