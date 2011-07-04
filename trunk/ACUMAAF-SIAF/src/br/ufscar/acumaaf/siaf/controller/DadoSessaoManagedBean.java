package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.DadoSessaoDAO;
import br.ufscar.acumaaf.siaf.model.Dadosessao;
import br.ufscar.acumaaf.siaf.model.Instrumentocoleta;
import br.ufscar.acumaaf.siaf.model.Sessao;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="dadosessaoMB")
@ViewScoped
public class DadoSessaoManagedBean {
	
	private Dadosessao dadoSessao = new Dadosessao();
	private Dadosessao dadoSessaoSelecionado = new Dadosessao();
	private DadoSessaoDAO dadoSessaoDao = new DadoSessaoDAO();
	private List<Dadosessao> listaDadoSessao;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Instrumentocoleta instrumentoColeta = new Instrumentocoleta();
	private List<Instrumentocoleta> listaInstrumentoColeta;
	private Sessao sessao = new Sessao();
	private List<Sessao> listaSessao;
	private boolean ativo = false;
	
	
	// Construtor da Classe ManagedBean
	public DadoSessaoManagedBean() {
		listaDadoSessao = new ArrayList<Dadosessao>();
		listaUsuarios = new ArrayList<Usuario>();
		listaInstrumentoColeta = new ArrayList<Instrumentocoleta>();
		listaSessao = new ArrayList<Sessao>();
	}
	
	public Dadosessao getDadoSessao() {
		return dadoSessao;
	}

	public void setDadoSessao(Dadosessao dadoSessao) {
		this.dadoSessao = dadoSessao;
	}

	public Dadosessao getDadoSessaoSelecionado() {
		return dadoSessaoSelecionado;
	}

	public void setDadoSessaoSelecionado(Dadosessao dadoSessaoSelecionado) {
		this.dadoSessaoSelecionado = dadoSessaoSelecionado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Instrumentocoleta getInstrumentoColeta() {
		return instrumentoColeta;
	}

	public void setInstrumentoColeta(Instrumentocoleta instrumentoColeta) {
		this.instrumentoColeta = instrumentoColeta;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Dadosessao> getListaDadoSessao() {
		listaDadoSessao = (List<Dadosessao>) dadoSessaoDao.findDadosSessaoAll(); 
		return listaDadoSessao;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll(); 
		return listaUsuarios;
	}

	public List<Instrumentocoleta> getListaInstrumentoColeta() {
		listaInstrumentoColeta = (List<Instrumentocoleta>) new br.ufscar.acumaaf.siaf.dao.InstrumentoColetaDAO().findInstrumentoColetaAll(); 
		return listaInstrumentoColeta;
	}

	public List<Sessao> getListaSessao() {
		listaSessao = (List<Sessao>) new br.ufscar.acumaaf.siaf.dao.SessaoDAO().findSessaoAll(); 
		return listaSessao;
	}

	//Método chamado pela página cadastrarDadoSessao.xhtml para cadastrar um novo dado da sessão de atividade física
	public String cadastrarDadoSessaoAtividadeFisica(){
		
		//Instancia com os dados selecionados
		dadoSessao.setSessao(sessao);
		dadoSessao.setUsuario(usuario);
		dadoSessao.setInstrumentocoleta(instrumentoColeta);
				
		System.out.println("Código.................:" + dadoSessao.getCodigodadosessao());
		System.out.println("Sessão.................:" + dadoSessao.getSessao().getData());
		System.out.println("Usuário................:" + dadoSessao.getUsuario().getNome());
		System.out.println("Instrumento de coleta..:" + dadoSessao.getInstrumentocoleta().getNome());
		System.out.println("Valor..................:" + dadoSessao.getValor());
		System.out.println("Descrição..............:" + dadoSessao.getDescricao());
		
		//Persiste a associação no banco de dados
		dadoSessaoDao.save(dadoSessao);
		
		return "/dadosessaoatividadefisica/listarDadosSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		dadoSessaoDao.delete(dadoSessaoSelecionado);		
	}
	
	public void editar(){
		
		dadoSessaoDao.update(dadoSessaoSelecionado);		
	}
	
	public String chamaPaginaGerenciarDadoSessaoAtividadeFisica(){
		
		return "/dadosessaoatividadefisica/gerenciaDadoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarDadoSessaoAtividadeFisica(){
		
		return "/dadosessaoatividadefisica/cadastrarDadoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarDadosSessaoAtividadeFisica(){
		
		return "/dadosessaoatividadefisica/listarDadosSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}	
}
