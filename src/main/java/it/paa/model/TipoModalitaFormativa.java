package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_modalita_formativa")
@NamedQuery(name = "TipoModalitaFormativa.findAll", query = "SELECT t FROM TipoModalitaFormativa t")
public class TipoModalitaFormativa extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @NotNull
    public String descrizione;

    @ManyToOne
    @JoinColumn(name = "classe_modalita_formativa")
    public ClasseModalitaFormativa classeModalitaFormativa;


}
