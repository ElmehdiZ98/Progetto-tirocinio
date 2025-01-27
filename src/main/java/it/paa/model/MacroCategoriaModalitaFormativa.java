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
@Table(name = "macrocategoria_mod_form")
@NamedQuery(name = "MacroCategoriaModalitaFormativa.findAll", query = "SELECT t FROM MacroCategoriaModalitaFormativa t")
public class MacroCategoriaModalitaFormativa extends PanacheEntity {

    @Column(name = "codice")
    public String codice;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "macroCategoriaModalitaFormativa")
    public List<ClasseModalitaFormativa> classiModalitaFormativa = new ArrayList<>();



}