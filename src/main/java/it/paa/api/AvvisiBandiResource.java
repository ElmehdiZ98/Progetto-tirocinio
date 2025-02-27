package it.paa.api;




import io.quarkus.security.Authenticated;
import it.paa.ExceptionProjectConstant;
import it.paa.dto.AvvisiBandiDTO;
import it.paa.dto.AvvisoBandoDettaglioDTO;
import it.paa.model.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static it.paa.model.ProceduraAttivazione.mapArticolazioniToDto;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/announcements")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvvisiBandiResource {

    /*
    *  metodo che permette di recuperare la lista dei avvisi e bandi
    *   API Lista Avvisi e Bandi
    */
    @GET
    @Path("/all")
    @Operation(summary = "Ottiene la lista Avvisi e Bandi")
    @APIResponse(responseCode = "200", description = "Lista ottenuta correttamente", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @APIResponse(responseCode = "404", description = "Nessun lista trovata")
    public List<AvvisiBandiDTO> getAnnouncements() {
        List<ProceduraAttivazione> procedureAttivazioni = ProceduraAttivazione.listAll();
        return procedureAttivazioni.stream()
                .flatMap(procedure -> mapArticolazioniToDto(procedure).stream())
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{proAtt-Id}")
    @Operation(summary = "Trova ProceduraAttivazione per ID", description = "Restituisce i dettagli di un ProceduraAttivazione dato il suo ID")
    @APIResponse(responseCode = "200", description = "ProceduraAttivazione trovato", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "ProceduraAttivazione non trovato")
    public Response getProgettoById(@Parameter(description = "ID del progetto", required = true)
                                    @PathParam("proAtt-Id") Long id) {
        ProceduraAttivazione proAtt = ProceduraAttivazione.findById(id);
        if (proAtt == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_NOT_FOUND_MESSAGE)
                    .build();
        }
        return Response.ok(proAtt).build();
    }


    @GET
    @Path("/{proAtt-Id}/details")
    @Operation(summary = "Ottiene i dettagli di un avviso/bando a partire dalL' ID")
    @APIResponse(responseCode = "200", description = "Dettagli ottenuti correttamente",
            content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = AvvisoBandoDettaglioDTO.class)))
    @APIResponse(responseCode = "404", description = "Avviso/Bando non trovato")
    public Response getAnnouncementDetails(@Parameter(description = "id", required = true)
                                           @PathParam("proAtt-Id") Long id) {
        ProceduraAttivazione procedura = ProceduraAttivazione.findById(id);

        if (procedura == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AvvisoBandoDettaglioDTO dettaglioDTO = getAvvisiBandiDTO(procedura);
        return Response.ok(dettaglioDTO).build();
    }

    private static AvvisoBandoDettaglioDTO getAvvisiBandiDTO(ProceduraAttivazione procedura) {

        AvvisoBandoDettaglioDTO dettaglioDTO = new AvvisoBandoDettaglioDTO();
        dettaglioDTO.setDescrizione(procedura.getDescrizione());
        dettaglioDTO.setResponsabileProcedimento(procedura.getResponsabileProcedimento());
        dettaglioDTO.setStartDatetime(procedura.getStartDatetime());
        dettaglioDTO.setEndDatetime(procedura.getEndDatetime());
        dettaglioDTO.setImportoTotale(procedura.getImportoTotale());
        dettaglioDTO.setAiuto(procedura.getAiuto());


        Progetto progetto = procedura.getProgettoAttivazione();
        if (progetto != null) {
            TipoInvestimento tipoInvestimento = progetto.getTipoInvestimento();
            if (tipoInvestimento != null) {
                // Recupero il TipoNaturaCup associato al TipoInvestimento
                TipoNaturaCup tipoNaturaCup = tipoInvestimento.getTipoNaturaCup();
                if (tipoNaturaCup != null) {
                    dettaglioDTO.setTipoNaturaCup(tipoNaturaCup);
                }
            }
            TipoAiuto tipoAiuto = progetto.getTipoAiuto();
            if (tipoAiuto != null) {
                dettaglioDTO.setTipoAiuto(tipoAiuto);
            }
            boolean isProposta = progetto.isPropostaProgetto();
            System.out.println("Il progetto è una proposta progettuale? " + isProposta);
            dettaglioDTO.setPropostaProgettuale(progetto.isPropostaProgetto());
        }

        AttoProceduraAttivazione attoProceduraAttivazione = procedura.getAtto();
        if (attoProceduraAttivazione != null) {
            dettaglioDTO.setDataAtto(attoProceduraAttivazione.getDataAtto());
            dettaglioDTO.setNumeroAtto(attoProceduraAttivazione.getNumeroAtto());
            dettaglioDTO.setTipoAttoProcedura(attoProceduraAttivazione.getTipoAttoProcedura());
        }

        return dettaglioDTO;


    }
}






























