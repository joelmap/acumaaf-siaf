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

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

@Path("sincronizaUsuario")
public class WsSincronizaTabelaUsuario {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelaUsuario() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of WsSincronizaTabelaUsuario
     * @return an instance of each Tables
     */
    @GET
    @Produces("application/json")
    public List<UsuarioJson> getJson() {
    	List<UsuarioJson> listaUsuarios = new ArrayList<UsuarioJson>();
    	System.out.println("Tamanho da lista: " + new UsuarioDAO().findListaUsuariosNaoSincronizado().size());
    	for (Usuario user : new UsuarioDAO().findListaUsuariosNaoSincronizado()) {
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
     * PUT method for updating or creating an instance of WsSincronizaTabelaUsuario
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public String putJson(String string) {
    	System.out.println("Entrou no método PUT do servidor! " + '\n' + "String = " + string);
    	JSONObject json;
		try{
			json = new JSONObject(string);
			JSONArray nameArray = json.names();
			System.out.println("nameArray: " + nameArray);
			JSONArray arrayVal = json.getJSONArray("usuarioJson");
			System.out.println("arrayVal: " + arrayVal);
			System.out.println("tamanho do arrayVal: " + arrayVal.length());
			for (int j = 0; j < arrayVal.length(); j++) {
				Usuario u = new Usuario();
				u.setCodigousuario(arrayVal.getJSONObject(j).getInt("codigousuario"));
				u = new UsuarioDAO().findUserByCode(u.getCodigousuario());
				System.out.println("Código.: " + u.getCodigousuario() + " | Nome: " + u.getNome());
				u.setSincronizado(true);
				new UsuarioDAO().update(u);
			}	
		}catch(JSONException e) {
			e.printStackTrace();
		}
    	return "Cadastros recebidos com sucesso!";
    }

}