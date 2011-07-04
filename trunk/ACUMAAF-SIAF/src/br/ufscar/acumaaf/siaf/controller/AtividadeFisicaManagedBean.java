package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.AtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Atividadefisica;

@ManagedBean(name="atividadefisicaMB")
@ViewScoped
public class AtividadeFisicaManagedBean {
	
	private Atividadefisica atividadeFisica = new Atividadefisica();
	private AtividadeFisicaDAO atividadeFisicaDAO = new AtividadeFisicaDAO();
	private List<Atividadefisica> listaAtividadeFisica;
	private Atividadefisica atividadeFisicaSelecionada;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public AtividadeFisicaManagedBean() {
		listaAtividadeFisica = new ArrayList<Atividadefisica>();
	}

	public Atividadefisica getAtividadeFisica() {
		return atividadeFisica;
	}

	public void setAtividadeFisica(Atividadefisica atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}
	
	public List<Atividadefisica> getListaAtividadeFisica() {
		listaAtividadeFisica = atividadeFisicaDAO.findAtividadeFisicaAll();
		return listaAtividadeFisica;
	}

	public Atividadefisica getAtividadeFisicaSelecionada() {
		return atividadeFisicaSelecionada;
	}

	public void setAtividadeFisicaSelecionada(Atividadefisica atividadeFisicaSelecionada) {
		this.atividadeFisicaSelecionada = atividadeFisicaSelecionada;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método para cadastrar uma nova Atividade Física
	public String cadastrarAtividadeFisica(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "atividadefisica" do SIAF e do SMC consistentes
		atividadeFisica.setSincronizado(false);
		
		//System.out.println("Código...........:" + atividadeFisica.getCodigoatividadefisica());
		//System.out.println("Nome.............:" + atividadeFisica.getNome());
		//System.out.println("Descrição........:" + atividadeFisica.getDescricao());
		//System.out.println("Sincronizado?....:" + atividadeFisica.getSincronizado());
		
		//Persiste as informações no banco de dados
		atividadeFisicaDAO.save(atividadeFisica);
		
		return "/atividadefisica/listarAtividadesFisicas.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de uma Atividade Física selecionada pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para false para que o SMC sincronize as informações de atualização
		atividadeFisicaSelecionada.setSincronizado(false);
		//Atualiza as informações no registro do banco de dados
		atividadeFisicaDAO.update(atividadeFisicaSelecionada);
	}
	
	//Método chamado para deletar uma Atividade Física selecionada pelo usuário
	public void deletar(){
		
		atividadeFisicaDAO.delete(atividadeFisicaSelecionada);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastro de Atividade Física
	public String chamaPaginaCadastrarAtividadeFisica(){
		
		return "/atividadefisica/cadastrarAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que lista todas as Atividades Físicas cadastradas
	public String chamaPaginaListarAtividadesFisicas(){
		
		return "/atividadefisica/listarAtividadesFisicas.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que gerencia os registros do IPAQ
	public String chamaPaginaGerenciarIpaq(){
		
		return "/ipaq/gerenciaIpaq.xhtml?faces-redirect=true";
	}
}
