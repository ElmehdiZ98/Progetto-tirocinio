package it.paa.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CourseDTO {

    public String codiceCorso;

    public String titoloCorso;

    public String macroCategoriaModalitaFormativa;

    public Date dataInizio;

    public Date dataFine;

    public Integer durata;


}
