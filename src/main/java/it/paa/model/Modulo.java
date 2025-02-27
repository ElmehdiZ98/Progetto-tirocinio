package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "modulo")
@NamedQuery(name = "Modulo.findAll", query = "SELECT m FROM Modulo m")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_modulo")
public class Modulo extends PanacheEntity {


    @ManyToOne
    @JoinColumn(name = "corso_id", nullable = false)  // Nome della colonna in DB rimane invariato
    @NotNull(message = "Il Corso è obbligatorio")
    public Corso corso;  // Modifica qui: usa "corso" con la lettera minuscola

    @NotNull
    @Column(name = "titolo")
    @Size(max = 255)
    public String titolo;

    @Column(name = "numero_ore")
    public BigDecimal numeroOre;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inizio")
    @NotNull(message = "la data di inizio modulo è obbligatoria")
    public Date dataInizio;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fine")
    @NotNull(message = "la data di fine modulo è obbligatoria")
    public Date dataFine;

    @ManyToMany
    @JoinTable(
            name = "modulo_tipo_metodologia",
            joinColumns = @JoinColumn(name = "modulo_id"),
            inverseJoinColumns = @JoinColumn(name = "tipo_metodologia_id")
    )
    public List<TipoMetodologia> metodologia;


}
