package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "tipo_investimento")
public class TipoInvestimento extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;
    @ManyToOne
    @JoinColumn(name = "tipo_natura_cup_id")
    public TipoNaturaCup tipoNaturaCup;

    @ManyToOne
    @JoinColumn(name = "tipo_cup_id")
    public TipoCup tipoCup;


}