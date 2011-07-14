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

import br.ufscar.acumaaf.siaf.dao.SessaoDAO;
import br.ufscar.acumaaf.siaf.model.Sessao;
import br.ufscar.acumaaf.siaf.model.SessaoJson;


@Path("sincronizaSessao")
public class WsSincronizaTabelaSessao {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelaSessao() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Retrieves representation of an instance of WsSincronizaTabelaSessao
     * @return an instance of String
     */
    @GET
    @Produces("application/json")
    public List<SessaoJson> getJson() {
    	List<SessaoJson> listaSessoes = new ArrayList<SessaoJson>();
    	System.out.println("Tamanho da lista: " + new SessaoDAO().findListaSessoesNaoSincronizadas());
    	for (Sessao sessao : new SessaoDAO().findListaSessoesNaoSincronizadas()) {
    		//Objeto Sessao mapeado para json
    		SessaoJson sJason = new SessaoJson();
    		sJason.setCodigosessao(sessao.getCodigosessao());
    		sJason.setData(sessao.getData());
    		sJason.setCodigogrupoatividadefisica(sessao.getGrupoatividadefisica().getCodigogrupoatividadefisica());
    		listaSessoes.add(sJason);
		}
        return listaSessoes;
    }

    /**
     * PUT method for updating or creating an instance of WsSincronizaTabelaSessao
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
			JSONArray arrayVal = json.getJSONArray("sessaoJson");
			System.out.println("arrayVal: " + arrayVal);
			System.out.println("tamanho do arrayVal: " + arrayVal.length());
			for (int j = 0; j < arrayVal.length(); j++) {
				Sessao s = new Sessao();
				s.setCodigosessao(arrayVal.getJSONObject(j).getInt("codigosessao"));
				s = new SessaoDAO().findSessaoByCode(s.getCodigosessao());
				System.out.println("Código.: " + s.getCodigosessao() + " | Nome: " + s.getData());
				s.setSincronizado(true);
				new SessaoDAO().update(s);
			}	
		}catch(JSONException e) {
			e.printStackTrace();
		}
    	return "Cadastros recebidos com sucesso!";
    }

}