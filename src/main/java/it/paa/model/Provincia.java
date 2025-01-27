package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "provincia")
@NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
public class Provincia extends PanacheEntity {

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_creazione", length = 19)
    public Date dataCreazione;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_modifica", length = 19)
    public Date dataModifica;

    @Column(name = "deleted")
    public Boolean deleted;

    @Column(name = "codice_istat")
    public String codiceIstat;

    @Column(name = "denominazione")
    public String denominazione;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "regione")
    public Regione regione;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia")
    public List<Comune> comuni = new ArrayList<>();



}
