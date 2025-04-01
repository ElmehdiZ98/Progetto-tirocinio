package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@MongoEntity(collection = "Btw12gre")
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

    public UUID btw12greId;

    public UUID btw12gre2;

    public UUID btw12gre3;

    public UUID btw12gre4;

    public UUID btw12gre5;

    public UUID btw12gre6;

    public UUID btw12gre7;

    public UUID btw12gre8;

    public UUID btw12gre9;

    public UUID btw12gre10;

    public UUID btw12gre11;

    public UUID btw12gre12;

    public UUID btw12gre13;


}
