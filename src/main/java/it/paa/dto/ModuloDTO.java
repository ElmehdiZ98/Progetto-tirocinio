package it.paa.dto;

import it.paa.model.TipoMetodologia;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class ModuloDTO {

    public String titoloModulo;

    public BigDecimal numeroOre;

    public Date dataInizioModulo;

    public Date dataFineModulo;

    public List<TipoMetodologia> metodologie;


}