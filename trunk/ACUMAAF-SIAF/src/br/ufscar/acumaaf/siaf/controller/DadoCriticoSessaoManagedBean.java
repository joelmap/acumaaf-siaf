package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.DadoCriticoSessaoDAO;
import br.ufscar.acumaaf.siaf.model.Dadocriticosessao;
import br.ufscar.acumaaf.siaf.model.Dadosessao;


@ManagedBean(name="dadocriticosessaoMB")
@ViewScoped
public class DadoCriticoSessaoManagedBean {
	
	private Dadocriticosessao dadoCriticoSessao = new Dadocriticosessao();
	private Dadocriticosessao dadoCriticoSessaoSelecionado = new Dadocriticosessao();
	private DadoCriticoSessaoDAO dadoCriticoSessaoDao = new DadoCriticoSessaoDAO();
	private List<Dadocriticosessao> listaDadoCriticoSessao;
	private Dadosessao dadoSessao = new Dadosessao();
	private List<Dadosessao> listaDadoSessao;
	
	// Construtor da Classe ManagedBean
	public DadoCriticoSessaoManagedBean() {
		listaDadoCriticoSessao = new ArrayList<Dadocriticosessao>();
		listaDadoSessao = new ArrayList<Dadosessao>();
	}
	
	public Dadocriticosessao getDadoCriticoSessao() {
		return dadoCriticoSessao;
	}

	public void setDadoCriticoSessao(Dadocriticosessao dadoCriticoSessao) {
		this.dadoCriticoSessao = dadoCriticoSessao;
	}
	
	public Dadocriticosessao getDadoCriticoSessaoSelecionado() {
		return dadoCriticoSessaoSelecionado;
	}

	public void setDadoCriticoSessaoSelecionado(
			Dadocriticosessao dadoCriticoSessaoSelecionado) {
		this.dadoCriticoSessaoSelecionado = dadoCriticoSessaoSelecionado;
	}

	public List<Dadocriticosessao> getListaDadoCriticoSessao() {
		listaDadoCriticoSessao = (List<Dadocriticosessao>) dadoCriticoSessaoDao.findDadosCriticosSessaoAll(); 
		return listaDadoCriticoSessao;
	}
	
	public Dadosessao getDadoSessao() {
		return dadoSessao;
	}

	public void setDadoSessao(Dadosessao dadoSessao) {
		this.dadoSessao = dadoSessao;
	}

	public List<Dadosessao> getListaDadoSessao() {
		listaDadoSessao = (List<Dadosessao>) new br.ufscar.acumaaf.siaf.dao.DadoSessaoDAO().findDadosSessaoAll(); 
		return listaDadoSessao;
	}

	//Método chamado pela página cadastrarDadoCriticoSessao.xhtml para cadastrar um novo dado da sessão de atividade física
	public String cadastrarDadoCriticoSessaoAtividadeFisica(){
		
		//Instancia com os dados selecionados
		dadoCriticoSessao.setDadosessao(dadoSessao);
				
		System.out.println("Código.................:" + dadoCriticoSessao.getCodigodadocriticosessao());
		System.out.println("DadoSessao.............:" + dadoCriticoSessao.getDadosessao().getCodigodadosessao());
				
		//Persiste a associação no banco de dados
		dadoCriticoSessaoDao.save(dadoCriticoSessao);
		
		return "/dadocriticosessaoatividadefisica/listarDadosCriticosSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		dadoCriticoSessaoDao.delete(dadoCriticoSessaoSelecionado);		
	}
		
	public String chamaPaginaGerenciarDadoCriticoSessaoAtividadeFisica(){
		
		return "/dadocriticosessaoatividadefisica/gerenciaDadoCriticoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarDadoCriticoSessaoAtividadeFisica(){
		
		return "/dadocriticosessaoatividadefisica/cadastrarDadoCriticoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarDadosCriticosSessaoAtividadeFisica(){
		
		return "/dadocriticosessaoatividadefisica/listarDadosCriticosSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}	
}
