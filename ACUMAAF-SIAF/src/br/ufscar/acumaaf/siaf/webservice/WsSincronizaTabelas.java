package br.ufscar.acumaaf.siaf.webservice;

import java.util.ArrayList;
import java.util.List;

import br.ufscar.acumaaf.siaf.dao.UsuarioDAO;
import br.ufscar.acumaaf.siaf.model.Usuario;
import br.ufscar.acumaaf.siaf.model.UsuarioJson;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

@Path("sincroniza")
public class WsSincronizaTabelas {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelas() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of WsSincronizaTabelas
     * @return an instance of each Tables
     */
    @GET
    @Produces("application/json")
    public List<UsuarioJson> getJson() {
    	List<UsuarioJson> listaUsuarios = new ArrayList<UsuarioJson>();
    	for (Usuario user : new UsuarioDAO().findUserAll()) {
    		//Objeto Usuário mapeado para json
    		UsuarioJson uJason = new UsuarioJson();
    		uJason.setCodigousuario(user.getCodigousuario());
    		uJason.setNome(user.getNome());
    		uJason.setUsuario(user.getUsuario());
    		uJason.setSenha(user.getSenha());
    		uJason.setTipousuario(user.getTipodocumento());
    		listaUsuarios.add(uJason);
		}
        return listaUsuarios;
    }

    /**
     * PUT method for updating or creating an instance of WsSincronizaTabelas
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(Usuario content) {
    }

}