package it.paa.api;

import it.paa.ExceptionProjectConstant;
import it.paa.dto.DettaglioProgettoDTO;
import it.paa.model.Progetto;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/projects/project")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Transactional
public class ProgettoResource {


    @GET
    @Path("/verify-project-code/{uniqueCode}")
    @Operation(summary = "Verifica l'esistenza di un codice locale progetto", description = "Controlla se il codice locale progetto fornito esiste nel sistema MOSEM")
    @APIResponse(responseCode = "200", description = "Codice locale progetto trovato", content = @Content(mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Codice locale progetto non trovato")
    public Response VerifyCodeProject(@Parameter(description = "Project code local", required = true)
                                          @PathParam("uniqueCode") String codiceLocaleProgetto) {

        Progetto progetto = Progetto.find("codiceLocaleProgetto", codiceLocaleProgetto).firstResult();
        if (progetto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_CODE_NOT_FOUND_MESSAGE)
                    .build();
        }
        return Response.ok(ExceptionProjectConstant.PROJECT_CODE_VALID_MESSAGE).build();
    }

    /**
     * API RESTfull dettaglio progetto
     */

    @GET
    @Path("/details-project/{codice}")
    public Response DettaglioProgetto(@PathParam("codice" ) String codiceLocaleProgetto) {
        Progetto progetto = Progetto.find("codiceLocaleProgetto",codiceLocaleProgetto).firstResult();
        if (progetto == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ExceptionProjectConstant.PROJECT_CODE_NOT_FOUND_MESSAGE)
                    .build();
        }
        DettaglioProgettoDTO progettoDTO = new DettaglioProgettoDTO();
        progettoDTO.setTitolo(progetto.getTitolo());
        progettoDTO.setSintesiProgetto(progetto.getSintesiProgetto());
        progettoDTO.setStartDatetime(progettoDTO.getProcedura().getStartDatetime());
        return Response.ok(progettoDTO).build();
    }
}