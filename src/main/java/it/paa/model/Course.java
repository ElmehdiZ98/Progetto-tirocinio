package it.paa.model;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import it.paa.dto.CourseDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "corso")
@NamedQuery(name="Course.findAll",query = "SELECT c FROM Course c")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
@AuditTable(value = "history_corso")
public class Course extends PanacheEntity {


    @NotNull
    @Column(name = "codice_corso")
    @Length(max = 60)
    public String codiceCorso;      //codice corso

    @NotNull
    @Column(name = "titolo_corso")
    @Length(max = 1000)
    public String titoloCorso;     //Titolo corso

    @ManyToOne
    @JoinColumn(name = "tipo_modalita_formativa_id")
    public TipoModalitaFormativa tipoModalitaFormativa;  //Tipo modalita formativa

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_inizio")
    public Date dataInizio;         //data inizio

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(name = "data_fine")
    public Date dataFine;           //data fine

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_criterio_selezione_id")
    public TipoCriterioSelezione tipoCriterioSelezione;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_contenuto_formativo_id")
    public TipoContenutoFormativo tipoContenutoFormativo;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "tipo_attestazione_finale_id")
    public TipoAttestazioneFinale tipoAttestazioneFinale;

    @ManyToOne
    @JoinColumn(name = "qualifica")
    public TipoQualifica qualifica;  //

    @NotNull
    @Min(value = 0)
    @Column(name = "ore_aula_fad")
    public Integer oreAulaFad = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "ore_stage_tirocinio")
    public Integer oreStageTirocinio = 0;

    @NotNull
    @Min(value = 0)
    @Column(name = "ore_laboratorio")
    public Integer oreLaboratorio = 0;

    @NotNull
    @Min(value = 1)
    @Column(name = "n_docenti_formatori_tutor")
    private Integer numeroDocentiFormatoriTutor;

    @Column(name = "max_allievi")
    @Min(value = 0)
    private Integer maxAllievi;

    @NotNull
    @Column(name = "durata")
    public Integer durata = 0;     //durata totale corso

    @OneToMany(mappedBy = "corso", fetch = FetchType.LAZY)
    public List<Modulo> modulo;




    public static CourseDTO toDTO(Course course) {
        CourseDTO dto = new CourseDTO();
        dto.codiceCorso = course.getCodiceCorso();
        dto.titoloCorso = course.getTitoloCorso();
        dto.macroCategoriaModalitaFormativa = String.valueOf(course.getTipoModalitaFormativa()
                .getClasseModalitaFormativa()
                .getMacroCategoriaModalitaFormativa());
        dto.dataInizio = course.getDataInizio();
        dto.dataFine = course.getDataFine();
        dto.durata = course.getDurata();

        return dto;
    }




}
