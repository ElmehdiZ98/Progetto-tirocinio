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
@Table(name = "class_prog_progr")
@NamedQuery(name = "ClassificazioneProgettoProgramma.findAll", query = "SELECT cpp FROM "
        + "ClassificazioneProgettoProgramma cpp")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_class_prog_progr")
public class ClassificazioneProgettoProgramma extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "articolazione_id")
    public ProgettoProgramma progettoProgramma;

    @ManyToOne
    @JoinColumn(name = "tipo_campo_intervento_id")
    public TipoCampoIntervento tipoCampoIntervento;

    @ManyToOne
    @JoinColumn(name = "tipo_forme_finanziamento_id")
    public TipoFormeFinanziamento tipoFormeFinanziamento;

    @ManyToOne
    @JoinColumn(name = "tipo_dim_tem_sec_id")
    public TipoDimensioneTematicaSecondaria tipoDimensioneTematicaSecondaria;

    @ManyToOne
    @JoinColumn(name = "tipo_risultato_atteso_id")
    public TipoRisultatoAtteso tipoRisultatoAtteso;




}
