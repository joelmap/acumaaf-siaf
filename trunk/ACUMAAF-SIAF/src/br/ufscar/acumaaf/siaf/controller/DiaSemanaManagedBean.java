package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufscar.acumaaf.siaf.dao.DiaSemanaDAO;
import br.ufscar.acumaaf.siaf.model.Diasemana;

@ManagedBean(name="diasemanaMB")
@ViewScoped
public class DiaSemanaManagedBean {
	
	private Diasemana diaSemana = new Diasemana();
	private DiaSemanaDAO diaSemanaDao = new DiaSemanaDAO();
	private List<Diasemana> listaDiaSemana;
	private Diasemana diaSemanaSelecinado;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public DiaSemanaManagedBean() {
		listaDiaSemana = new ArrayList<Diasemana>();
	}
	
	public Diasemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Diasemana diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	public Diasemana getDiaSemanaSelecinado() {
		return diaSemanaSelecinado;
	}

	public void setDiaSemanaSelecinado(Diasemana diaSemanaSelecinado) {
		this.diaSemanaSelecinado = diaSemanaSelecinado;
	}

	public List<Diasemana> getListaDiaSemana() {
		listaDiaSemana = (List<Diasemana>) diaSemanaDao.findDiaSemanaAll();
		return listaDiaSemana;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método para cadastrar um registro de Dias da Semana
	public String cadastrarDiaSemana(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "diasemana" do SIAF e do SMC consistentes
		diaSemana.setSincronizado(false);
		
		System.out.println("Código...........:" + diaSemana.getCodigodiasemana());
		System.out.println("Segunda..........:" + diaSemana.getSegunda());
		System.out.println("Terça............:" + diaSemana.getTerca());
		System.out.println("Quarta...........:" + diaSemana.getQuarta());
		System.out.println("Quinta...........:" + diaSemana.getQuinta());
		System.out.println("Sexta............:" + diaSemana.getSexta());
		System.out.println("Sábado...........:" + diaSemana.getSabado());
		System.out.println("Domingo..........:" + diaSemana.getDomingo());
		System.out.println("Sincronizado?....:" + diaSemana.getSincronizado());
		
		//Verifica se o usuário esqueceu de selecionar pelo menos um dia para realização das atividades físicas
		if(diaSemana.getSegunda() || diaSemana.getTerca() || diaSemana.getQuarta() || diaSemana.getQuinta() || diaSemana.getSexta() || diaSemana.getSabado() || diaSemana.getDomingo()){
			diaSemanaDao.save(diaSemana);
			return "/diasemana/listarDiasSemana.xhtml?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "[Atenção!] É necessário selecionar pelo menos um dia da semana.", "."));
			return "#";
		}				
	}
	
	//Método chamado para editar informações de um registro de Dias da Semana selecionado pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		diaSemanaSelecinado.setSincronizado(false);
		//Persiste as informações alteradas pelo usuário
		diaSemanaDao.update(diaSemanaSelecinado);
	}
	
	//Método chamado para deletar um registro de Dias da Semana selecionado pelo usuário
	public void deletar(){
		
		diaSemanaDao.delete(diaSemanaSelecinado);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastro de Dias da Semana
	public String chamaPaginaCadastrarDiaSemana(){
		
		return "/diasemana/cadastrarDiaSemana.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que lista todos os registros de Dias da Semana cadastrados
	public String chamaPaginaListarDiasSemana(){
		
		return "/diasemana/listarDiasSemana.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que gerencia os registros de Dias da Semana
	public String chamaPaginaGerenciarDiaSemana(){
		
		return "/diasemana/gerenciaDiasSemana.xhtml?faces-redirect=true";
	}
}
