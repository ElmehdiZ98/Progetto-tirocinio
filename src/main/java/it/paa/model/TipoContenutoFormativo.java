package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tipo_contenuto_formativo")
@NamedQuery(name = "TipoContenutoFormativo.findAll", query = "SELECT t FROM TipoContenutoFormativo t")
public class TipoContenutoFormativo extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @Column(name = "descrizione")
    public String descrizione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "settore_contenuto_formativo")
    public SettoreContenutoFormativo settoreContenutoFormativo;



}
