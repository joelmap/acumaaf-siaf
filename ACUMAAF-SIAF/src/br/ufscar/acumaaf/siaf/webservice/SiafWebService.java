package br.ufscar.acumaaf.siaf.webservice;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.primefaces.json.JSONArray;

import br.ufscar.acumaaf.siaf.dao.UsuarioDAO;
import br.ufscar.acumaaf.siaf.model.Usuario;

@Path("acumaafws")
public class SiafWebService {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public SiafWebService() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of SiafWebService
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public List getXml() {
    	return new UsuarioDAO().findUserAll();
    }

    /**
     * PUT method for updating or creating an instance of SiafWebService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

}