package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Btw12gre extends PanacheEntity {

    public String nome;

    public String descrizione;

    public String data;

    public double lat;

    public double lon;

    public int dfw2;
}
