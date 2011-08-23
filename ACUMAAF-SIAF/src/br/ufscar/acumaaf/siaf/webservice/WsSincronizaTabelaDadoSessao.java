package br.ufscar.acumaaf.siaf.webservice;

import br.ufscar.acumaaf.siaf.dao.DadoSessaoDAO;
import br.ufscar.acumaaf.siaf.dao.InstrumentoColetaDAO;
import br.ufscar.acumaaf.siaf.dao.PresencaDAO;
import br.ufscar.acumaaf.siaf.dao.SessaoDAO;
import br.ufscar.acumaaf.siaf.dao.UsuarioDAO;
import br.ufscar.acumaaf.siaf.model.DadoSessaoJson;
import br.ufscar.acumaaf.siaf.model.Dadosessao;
import br.ufscar.acumaaf.siaf.model.Presenca;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONException;
import org.primefaces.json.JSONObject;

@Path("sincronizaDadoSessao")
public class WsSincronizaTabelaDadoSessao {
    @SuppressWarnings("unused")
    @Context
    private UriInfo context;

    /**
     * Default constructor. 
     */
    public WsSincronizaTabelaDadoSessao() {
    }

    /**
     * PUT method for updating or creating an instance of WsSincronizaDadoSessao
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public String putJson(String string) {
    	System.out.println("Entrou no método PUT para sincronizar DadoSessao do servidor! " + '\n' + "String = " + string);
    	JSONObject json;
		try{
			json = new JSONObject(string);
			JSONArray nameArray = json.names();
			System.out.println("nameArray: " + nameArray);
			JSONArray arrayVal = json.getJSONArray("dadosessaoJson");
			System.out.println("arrayVal: " + arrayVal);
			System.out.println("tamanho do arrayVal: " + arrayVal.length());
			for (int j = 0; j < arrayVal.length(); j++) {
				DadoSessaoJson ds = new DadoSessaoJson();
				ds.setCodigoSessao(arrayVal.getJSONObject(j).getInt("codigosessao"));
				ds.setCodigoUsuario(arrayVal.getJSONObject(j).getInt("codigousuario"));
				ds.setCodigoInstrumentoColeta(arrayVal.getJSONObject(j).getInt("codigoinstrumentocoleta"));
				ds.setValor(arrayVal.getJSONObject(j).getString("valor"));
				Dadosessao dadoSessao = new Dadosessao();
				dadoSessao.setSessao(new SessaoDAO().findSessaoByCode(ds.getCodigoSessao()));
				dadoSessao.setUsuario(new UsuarioDAO().findUserByCode(ds.getCodigoUsuario()));
				dadoSessao.setInstrumentocoleta(new InstrumentoColetaDAO().findInstrumentoColetaByCode(ds.getCodigoInstrumentoColeta()));
				dadoSessao.setValor(ds.getValor());
				new DadoSessaoDAO().save(dadoSessao);
				Presenca presenca = new Presenca();
				presenca.setSessao(dadoSessao.getSessao());
				presenca.setUsuario(dadoSessao.getUsuario());
				presenca.setObservacao("Registro realizado pelo SMC");
				if(new PresencaDAO().findPresencaByCodeUserAndCodeSessao(presenca).isEmpty()){
					new PresencaDAO().save(presenca);	
				}
			}	
		}catch(JSONException e) {
			e.printStackTrace();
		}
    	return "Cadastros recebidos com sucesso!";
    }
}