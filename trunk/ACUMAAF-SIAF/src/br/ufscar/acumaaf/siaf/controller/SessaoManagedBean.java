package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.SessaoDAO;
import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.Sessao;


@ManagedBean(name="sessaoMB")
@ViewScoped
public class SessaoManagedBean {
	
	private Sessao sessao = new Sessao();
	private SessaoDAO sessaoDao = new SessaoDAO();
	private Sessao sessaoSelecionada = new Sessao();
	private List<Sessao> listaSessoes;
	private Grupoatividadefisica grupoAtividadeFisica = new Grupoatividadefisica();
	private List<Grupoatividadefisica> listaGrupoAtividadeFisica;
	private boolean ativo = false;
		
	// Construtor da Classe ManagedBean
	public SessaoManagedBean() {
		listaSessoes = new ArrayList<Sessao>();
		listaGrupoAtividadeFisica = new ArrayList<Grupoatividadefisica>();
	}
	
	public Grupoatividadefisica getGrupoAtividadeFisica() {
		return grupoAtividadeFisica;
	}

	public void setGrupoAtividadeFisica(Grupoatividadefisica grupoAtividadeFisica) {
		this.grupoAtividadeFisica = grupoAtividadeFisica;
	}

	public List<Grupoatividadefisica> getListaGrupoAtividadeFisica() {
		listaGrupoAtividadeFisica = (List<Grupoatividadefisica>) new br.ufscar.acumaaf.siaf.dao.GrupoAtividadeFisicaDAO().findGrupoAtividadeFisicaAll();
		return listaGrupoAtividadeFisica;
	}
	
	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public Sessao getSessaoSelecionada() {
		return sessaoSelecionada;
	}

	public void setSessaoSelecionada(Sessao sessaoSelecionada) {
		this.sessaoSelecionada = sessaoSelecionada;
	}

	public List<Sessao> getListaSessoes() {
		listaSessoes = sessaoDao.findSessaoAll();
		return listaSessoes;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método chamado pela página cadastrarSessaoAtividadeFisica.xhtml para cadastrar uma nova Sessão de Atividade Física
	public String cadastrarSessaoAtividadeFisica(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "sessao" do SIAF e do SMC consistentes
		sessao.setSincronizado(false);		
		
		//Grupo de Atividade Física que foi selecionado no banco de dados
		System.out.println("Codigo: " + grupoAtividadeFisica.getCodigogrupoatividadefisica());
		System.out.println("Nome..: " + grupoAtividadeFisica.getNome());
		
		//Instancia com os dados selecionados pelo usuário
		sessao.setGrupoatividadefisica(grupoAtividadeFisica);
				
		System.out.println("Código...........................:" + sessao.getCodigosessao());
		System.out.println("Código Grupo de atividade fisica.:" + sessao.getGrupoatividadefisica().getNome());
		System.out.println("Data.............................:" + sessao.getData());
		System.out.println("Observação.......................:" + sessao.getDescricao());
		System.out.println("Sincronizado?....................:" + sessao.getSincronizado());
		
		//Persiste a associação no banco de dados
		sessaoDao.save(sessao);
		
		return "/sessaoatividadefisica/listarSessoesAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de uma Sessao de Atividade Física selecionada pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para false para que o SMC sincronize as informações de atualização
		sessaoSelecionada.setSincronizado(false);
		//Atualiza as informações no registro do banco de dados
		sessaoDao.update(sessaoSelecionada);
	}
	
	public void deletar(){
		
		sessaoDao.delete(sessaoSelecionada);
	}
	
	public String chamaPaginaGerenciaSessaoAtividadeFisica(){
		
		return "/sessaoatividadefisica/gerenciaSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarSessaoAtividadeFisica(){
		
		return "/sessaoatividadefisica/cadastrarSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarSessoesAtividadeFisica(){
		
		return "/sessaoatividadefisica/listarSessoesAtividadeFisica.xhtml?faces-redirect=true";
	}	
}