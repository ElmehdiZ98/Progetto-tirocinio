package it.paa.model;

import com.google.errorprone.annotations.Immutable;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.type.descriptor.java.Immutability;

import java.util.UUID;

@Entity
@Getter
@Setter
@Cacheable
public class Tch13gfru extends PanacheEntity {

    public UUID title;

    public String desc;

    public Immutability imm;

    public String state;


}
