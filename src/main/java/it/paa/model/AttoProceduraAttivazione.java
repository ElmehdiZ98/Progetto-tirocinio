package it.paa.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.util.Date;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "atto_procedura_attivazione")
@NamedQuery(name = "AttoProceduraAttivazione.findAll", query = "SELECT p FROM AttoProceduraAttivazione p")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_atto_proc_att")
public class AttoProceduraAttivazione extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "tipo_atto_procedura_id")
    public TipoAttoProcedura tipoAttoProcedura;   // Tipo Atto

    @Column(name = "numero_atto")
    @Size(max = 255)
    public String numeroAtto;      //numero Atto

    @Temporal(TemporalType.DATE)
    @Column(name = "data_atto")
    public Date dataAtto;          //data atto


}
