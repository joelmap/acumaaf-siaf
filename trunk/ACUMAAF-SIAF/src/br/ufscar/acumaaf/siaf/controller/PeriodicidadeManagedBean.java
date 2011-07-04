package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.PeriodicidadeDAO;
import br.ufscar.acumaaf.siaf.model.Periodicidade;


@ManagedBean(name="periodicidadeMB")
@ViewScoped
public class PeriodicidadeManagedBean {
	
	private Periodicidade periodicidade = new Periodicidade();
	private PeriodicidadeDAO periodicidadeDao = new PeriodicidadeDAO();
	private List<Periodicidade> listaPeriodicidades;
	private Periodicidade periodicidadeSelecionada;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public PeriodicidadeManagedBean() {
		listaPeriodicidades = new ArrayList<Periodicidade>();
	}
		
	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}

	public Periodicidade getPeriodicidadeSelecionada() {
		return periodicidadeSelecionada;
	}

	public void setPeriodicidadeSelecionada(Periodicidade periodicidadeSelecionada) {
		this.periodicidadeSelecionada = periodicidadeSelecionada;
	}

	public List<Periodicidade> getListaPeriodicidades() {
		listaPeriodicidades = (List<Periodicidade>) periodicidadeDao.findPeriodicidadeAll();
		return listaPeriodicidades;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método para cadastrar um novo registro de Periodicidade
	public String cadastrarPeriodicidade(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "periodicidade" do SIAF e do SMC consistentes
		periodicidade.setSincronizado(false);
		
		//System.out.println("Código...........:" + periodicidade.getCodigoperiodicidade());
		//System.out.println("Nome.............:" + periodicidade.getNome());
		//System.out.println("Descrição........:" + periodicidade.getDescricao());
		//System.out.println("Sincronizado?....:" + periodicidade.getSincronizado());
		
		//Persiste as informações no banco de dados
		periodicidadeDao.save(periodicidade);
		
		return "/periodicidade/listarPeriodicidades.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de uma Periodicidade selecionada pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		periodicidadeSelecionada.setSincronizado(false);
		//Persiste as alterações no banco de dados
		periodicidadeDao.update(periodicidadeSelecionada);
	
	}
	
	//Método chamado para deletar uma Periodicidade selecionada pelo usuário
	public void deletar(){
		
		periodicidadeDao.delete(periodicidadeSelecionada);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastro de Periodicidade
	public String chamaPaginaCadastrarPeriodicidade(){
		
		return "/periodicidade/cadastrarPeriodicidade.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que lista todas as Periodicidades cadastradas
	public String chamaPaginaListarPeriodicidades(){
		
		return "/periodicidade/listarPeriodicidades.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que gerencia os registros de Periodicidade
	public String chamaPaginaGerenciarPeriodicidade(){
		
		return "/periodicidade/gerenciaPeriodicidade.xhtml?faces-redirect=true";
	}
}
