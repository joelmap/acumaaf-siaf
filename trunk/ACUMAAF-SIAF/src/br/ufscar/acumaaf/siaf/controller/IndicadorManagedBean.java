package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.IndicadorDAO;
import br.ufscar.acumaaf.siaf.model.Indicador;


@ManagedBean(name="indicadorMB")
@ViewScoped
public class IndicadorManagedBean {
	
	private Indicador indicador = new Indicador();
	private Indicador indicadorSelecionado = new Indicador();
	private IndicadorDAO indicadorDao = new IndicadorDAO();
	private List<Indicador> listaIndicador;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public IndicadorManagedBean() {
		listaIndicador = new ArrayList<Indicador>();
	}
	
	public Indicador getIndicador() {
		return indicador;
	}

	public void setIndicador(Indicador indicador) {
		this.indicador = indicador;
	}

	public Indicador getIndicadorSelecionado() {
		return indicadorSelecionado;
	}

	public void setIndicadorSelecionado(Indicador indicadorSelecionado) {
		this.indicadorSelecionado = indicadorSelecionado;
	}

	public List<Indicador> getListaIndicador() {
		listaIndicador = indicadorDao.findIndicadorAll();
		return listaIndicador;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método para cadastrar um novo Indicador
	public String cadastrarIndicador(){
		
		System.out.println("Código...........:" + indicador.getCodigoindicador());
		System.out.println("Nome.............:" + indicador.getNome());
		System.out.println("Descrição........:" + indicador.getDescricao());

		//Periste as informações no banco de dados
		indicadorDao.save(indicador);
		
		return "/indicador/listarIndicadores.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um Indicador selecionado pelo usuário
	public void editar(){
		
		indicadorDao.update(indicadorSelecionado);
	}
	
	//Método chamado para deletar um Indicador selecionado pelo usuário
	public void deletar(){
		
		indicadorDao.delete(indicadorSelecionado);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastro de Indicador
	public String chamaPaginaCadastrarIndicador(){
		
		return "/indicador/cadastrarIndicador.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que lista todos os Indicadores
	public String chamaPaginaListarIndicadores(){
		
		return "/indicador/listarIndicadores.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que gerencia os registros de Indicadores
	public String chamaPaginaGerenciarIndicador(){
		
		return "/indicador/gerenciaIndicador.xhtml?faces-redirect=true";
	}
}