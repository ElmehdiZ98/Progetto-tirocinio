package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


@Entity
@XmlRootElement
@Getter
@Setter
@Table(name = "art_proc_att")
@NamedQuery(name = "AssociazioneArticolazioneProceduraAttivazione.findAll",
        query = "SELECT associazione FROM AssociazioneArticolazioneProceduraAttivazione associazione")
public class AssociazioneArticolazioneProceduraAttivazione extends
        PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "articolazione_id", nullable = false)
    @NotNull
    public Articolazione articolazione;

    @ManyToOne
    @JoinColumn(name = "procedura_attivazione_id", nullable = false)
    @NotNull
    public ProceduraAttivazione proceduraAttivazione;




}

