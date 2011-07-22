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

import br.ufscar.acumaaf.siaf.dao.GrupoAtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.GrupoatividadefisicaJson;

@Path("sincronizaGrupoAtividadeFisica")
public class WsSincronizaTabelaGrupoAtividadeFisica {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelaGrupoAtividadeFisica() {
    }

    /**
     * Retrieves representation of an instance of WsSincronizaTabelaUsuario
     * @return an instance of each Tables
     */
    @GET
    @Produces("application/json")
    public List<GrupoatividadefisicaJson> getJson() {
    	List<GrupoatividadefisicaJson> listaGrupos = new ArrayList<GrupoatividadefisicaJson>();
    	System.out.println("Tamanho da lista: " + new GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaNaoSincronizado().size());
    	for (Grupoatividadefisica grupoAtividadeFisica : new GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaNaoSincronizado()) {
    		//Objeto grupoAtividadeFisica mapeado para json
    		GrupoatividadefisicaJson gafJason = new GrupoatividadefisicaJson();
    		gafJason.setCodigogrupoatividadefisica(grupoAtividadeFisica.getCodigogrupoatividadefisica());
    		gafJason.setNome(grupoAtividadeFisica.getNome());
    		gafJason.setHorainicio(grupoAtividadeFisica.getHorainicio().toString());
    		gafJason.setHorafim(grupoAtividadeFisica.getHorafim().toString());
    		listaGrupos.add(gafJason);
		}
        return listaGrupos;
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
			JSONArray arrayVal = json.getJSONArray("grupoatividadefisicaJson");
			System.out.println("arrayVal: " + arrayVal);
			System.out.println("tamanho do arrayVal: " + arrayVal.length());
			for (int j = 0; j < arrayVal.length(); j++) {
				Grupoatividadefisica gaf = new Grupoatividadefisica();
				gaf.setCodigogrupoatividadefisica(arrayVal.getJSONObject(j).getInt("codigogrupoatividadefisica"));
				gaf = new GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaByCode(gaf.getCodigogrupoatividadefisica());
				System.out.println("Código.: " + gaf.getCodigogrupoatividadefisica() + " | Nome: " + gaf.getNome());
				gaf.setSincronizado(true);
				new GrupoAtividadeFisicaDAO().update(gaf);
			}	
		}catch(JSONException e) {
			e.printStackTrace();
		}
    	return "Cadastros recebidos com sucesso!";
    }
}