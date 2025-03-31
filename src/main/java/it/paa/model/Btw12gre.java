package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

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

    public int dfw3;

    public int dfw4;

    public UUID btw12gre;
}
