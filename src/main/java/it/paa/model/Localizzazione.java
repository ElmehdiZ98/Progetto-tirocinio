package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "localizzazione")
@NamedQuery(name = "Localizzazione.findAll", query = "SELECT l FROM Localizzazione l")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_localizzazione")
public class Localizzazione extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "regione")
    public Regione regione;

    @ManyToOne
    @JoinColumn(name = "provincia")
    public Provincia provincia;

    @ManyToOne
    @JoinColumn(name = "comune")
    public Comune comune;
}
