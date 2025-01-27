package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "classe_modalita_formativa")
@NamedQuery(name = "ClasseModalitaFormativa.findAll", query = "SELECT t FROM ClasseModalitaFormativa t")
public class ClasseModalitaFormativa extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @ManyToOne
    @JoinColumn(name = "macrocategoria_mod_form")
    public MacroCategoriaModalitaFormativa macroCategoriaModalitaFormativa;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "classeModalitaFormativa")
    public List<TipoModalitaFormativa> tipiModalitaFormativa = new ArrayList<>();



}
