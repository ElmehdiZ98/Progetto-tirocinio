package it.paa.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@XmlRootElement
@Table(name = "class_art")
@NamedQuery(name = "ClassificazioneArticolazione.findAll", query = "SELECT ca FROM ClassificazioneArticolazione ca")
public class ClassificazioneArticolazione extends PanacheEntity {

    @OneToOne
    @JoinColumn(name = "articolazione_id", referencedColumnName = "id")
    public Articolazione articolazione;           //articolazione

    @ManyToMany
    @JoinTable(name = "class_campo_intervento_art", joinColumns = {
            @JoinColumn(name = "class_art_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tipo_campo_intervento_id", nullable = false)})
    public List<TipoCampoIntervento> tipoCampoInterventoList = new ArrayList<>();     //camo di intervento

    @ManyToMany
    @JoinTable(name = "class_forme_finanziamento_art", joinColumns = {
            @JoinColumn(name = "class_art_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tipo_forme_finanziamento_id", nullable = false)})
    public List<TipoFormeFinanziamento> tipoFormeFinanziamentoList = new ArrayList<>();     //forme finanziamento


    @ManyToMany
    @JoinTable(name = "class_dim_ter_sec_art", joinColumns = {
            @JoinColumn(name = "class_art_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tipo_dim_tem_sec_id", nullable = false)})
    public List<TipoDimensioneTematicaSecondaria> tipoDimensioneTematicaSecondariaList = new ArrayList<>();   // dimensione tematica secondaria

    @ManyToMany
    @JoinTable(name = "class_risultato_atteso_art", joinColumns = {
            @JoinColumn(name = "class_art_id", nullable = false)}, inverseJoinColumns = {
            @JoinColumn(name = "tipo_risultato_atteso_id", nullable = false)})
    public List<TipoRisultatoAtteso> tipoRisultatoAttesoList = new ArrayList<>();                //risultato atteso


}