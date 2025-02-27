package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.YesNoConverter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name = "tipo_natura_cup")
@NamedQuery(name = "TipoNaturaCup.findAll", query = "SELECT t FROM TipoNaturaCup t")
public class TipoNaturaCup extends PanacheEntity implements Serializable {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

    @Column(name = "has_aiuto")
    @Convert(converter = YesNoConverter.class)
    public Boolean hasAiuto = Boolean.FALSE;

    @Column(name = "is_strumento_finanziario")
    @Convert(converter = YesNoConverter.class)
    public Boolean isStrumentoFinanziario = Boolean.FALSE;

    @Column(name = "has_aiuto_for_ddp")
    @Convert(converter = YesNoConverter.class)
    public Boolean hasAiutoForDdp = Boolean.FALSE;

}