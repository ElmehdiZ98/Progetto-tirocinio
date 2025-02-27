package it.paa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SoggettoDTO{

    public String denominazione;

    public String tipoSoggettoDiritto;

    public String cfPiva;

    public String codiceIPA;

    public String tipoFormaGiuridica;

    public String nome;   // nome

    public String cognome;  //cognome

}