package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "localizzazione_progetto")
@NamedQuery(name = "LocalizzazioneProgetto.findAll", query = "SELECT lo FROM LocalizzazioneProgetto lo")
public class LocalizzazioneProgetto extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "progetto_id", referencedColumnName = "id")
    @NotNull(message = "Il progetto è obbligatorio")
    public Progetto progetto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "localizzazione_id")
    @Valid
    public Localizzazione localizzazione;

}
