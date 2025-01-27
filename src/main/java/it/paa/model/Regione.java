package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "regione")
@NamedQuery(name = "Regione.findAll", query = "SELECT r FROM Regione r")
public class Regione extends PanacheEntity {

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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "regione")
    public List<Provincia> province = new ArrayList<>();





}
