package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comune")
@NamedQuery(name = "Comune.findAll", query = "SELECT c FROM Comune c")
public class Comune extends PanacheEntity {

    @Column(name = "data_creazione")
    public Date dataCreazione;

    @Column(name = "data_modifica")
    public Date dataModifica;

    @Column(name = "deleted")
    public Boolean deleted;

    @Column(name = "codice_istat")
    @Size(max = 255)
    public String codiceIstat;

    @Column(name = "denominazione")
    @Size(max = 255)
    public String denominazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia")
    public Provincia provincia;

    @Column(name = "nuts_one")
    @Size(max = 255)
    public String nutsOne;

    @Column(name = "nuts_two")
    @Size(max = 255)
    public String nutsTwo;

    @Column(name = "nuts_three")
    @Size(max = 255)
    public String nutsThree;

    //CM0119-449
    @Column(name = "codice_cat")
    @Size(max = 4)
    public String codice_cat;



}
