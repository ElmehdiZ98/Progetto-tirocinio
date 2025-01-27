package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "proc_att_tipo_aiuto")
@NamedQuery(name = "ProceduraAttivazioneTipoAiuto.findAll", query = "SELECT p FROM ProceduraAttivazioneTipoAiuto p")
public class ProceduraAttivazioneTipoAiuto extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "procedura_attivazione_id", nullable = false)
    @NotNull
    public ProceduraAttivazione proceduraAttivazione;

    @ManyToOne
    @JoinColumn(name = "tipo_aiuto_id", nullable = false)
    @NotNull
    public TipoAiuto tipoAiuto;


}