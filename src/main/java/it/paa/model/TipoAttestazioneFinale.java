package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_attestazione_finale")
@NamedQuery(name = "TipoAttestazioneFinale.findAll", query = "SELECT t FROM TipoAttestazioneFinale t")
public class TipoAttestazioneFinale extends PanacheEntity {

    @Column(name = "codice")
    private String codice;

    @NotNull
    private String descrizione;

}
