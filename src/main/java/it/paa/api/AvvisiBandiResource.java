package it.paa.api;




import it.paa.dto.AvvisiBandiDTO;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import it.paa.model.ProceduraAttivazione;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static it.paa.model.ProceduraAttivazione.mapArticolazioniToDto;
import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AvvisiBandiResource {

    /*
    *  metodo che permette di recuperare la lista dei avvisi e bandi
    * API Lista Avvisi e Bandi
    * */
    @Operation(summary = "Ottiene la lista Avvisi e Bandi")
    @APIResponse(responseCode = "200", description = "Lista ottenuta correttamente", content = @Content(mediaType = APPLICATION_JSON, schema = @Schema(implementation = URI.class)))
    @APIResponse(responseCode = "404", description = "Nessun lista trovata")
    @GET
    @Path("/announcements/list")
    @Transactional
    public List<AvvisiBandiDTO> getAnnouncements() {
        List<ProceduraAttivazione> procedureAttivazioni = ProceduraAttivazione.listAll();
        return procedureAttivazioni.stream()
                .flatMap(procedure -> mapArticolazioniToDto(procedure).stream())
                .collect(Collectors.toList());
    }








}






























