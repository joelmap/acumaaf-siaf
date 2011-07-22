package br.ufscar.acumaaf.siaf.webservice;

import java.util.ArrayList;
import java.util.List;

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

import br.ufscar.acumaaf.siaf.dao.MatriculaUsuarioGrupoAtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Matriculausuariogrupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.MatriculausuariogrupoatividadefisicaJson;

@Path("sincronizaMatriculaUsuarioGrupoAtividadeFisica")
public class WsSincronizaTabelaMatriculaUsuarioGrupoAtividadeFisica {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelaMatriculaUsuarioGrupoAtividadeFisica() {
    }

    @GET
    @Produces("application/json")
    public List<MatriculausuariogrupoatividadefisicaJson> getJson() {
    	List<MatriculausuariogrupoatividadefisicaJson> listaMatriculas = new ArrayList<MatriculausuariogrupoatividadefisicaJson>();
    	System.out.println("Tamanho da lista: " + new MatriculaUsuarioGrupoAtividadeFisicaDAO().findListaMatriculaUsuarioGrupoAtividadeFisicaNaoSincronizado().size());
    	for (Matriculausuariogrupoatividadefisica mat : new MatriculaUsuarioGrupoAtividadeFisicaDAO().findListaMatriculaUsuarioGrupoAtividadeFisicaNaoSincronizado()) {
    		//Objeto Matriculausuariogrupoatividadefisica mapeado para json
    		MatriculausuariogrupoatividadefisicaJson mJason = new MatriculausuariogrupoatividadefisicaJson();
    		mJason.setCodigomatriculausuariogrupoatividadefisica(mat.getCodigomatriculausuariogrupoatividadefisica());
    		mJason.setCodigoGrupoAtividadeFisica(mat.getGrupoatividadefisica().getCodigogrupoatividadefisica());
    		mJason.setCodigoUsuario(mat.getUsuario().getCodigousuario());
    		listaMatriculas.add(mJason);
		}
        return listaMatriculas;
    }

    @PUT
    @Consumes("application/json")
    public String putJson(String string) {
    	System.out.println("Entrou no método PUT do servidor! " + '\n' + "String = " + string);
    	JSONObject json;
		try{
			json = new JSONObject(string);
			JSONArray nameArray = json.names();
			System.out.println("nameArray: " + nameArray);
			JSONArray arrayVal = json.getJSONArray("matriculausuariogrupoatividadefisicaJson");
			System.out.println("arrayVal: " + arrayVal);
			System.out.println("tamanho do arrayVal: " + arrayVal.length());
			for (int j = 0; j < arrayVal.length(); j++) {
				Matriculausuariogrupoatividadefisica  m = new Matriculausuariogrupoatividadefisica();
				m.setCodigomatriculausuariogrupoatividadefisica(arrayVal.getJSONObject(j).getInt("codigomatriculausuariogrupoatividadefisica"));
				m = new MatriculaUsuarioGrupoAtividadeFisicaDAO().findMatriculaUsuarioGrupoAtividadeFisicaByCode(m);
				System.out.println("Código.: " + m.getCodigomatriculausuariogrupoatividadefisica());
				m.setSincronizado(true);
				new MatriculaUsuarioGrupoAtividadeFisicaDAO().update(m);
			}	
		}catch(JSONException e) {
			e.printStackTrace();
		}
    	return "Cadastros recebidos com sucesso!";
    }

}