package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.DadoPadraoSessaoDAO;
import br.ufscar.acumaaf.siaf.model.Dadopadraosessao;
import br.ufscar.acumaaf.siaf.model.Instrumentocoleta;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="dadopadraosessaoMB")
@ViewScoped
public class DadoPadraoSessaoManagedBean {
	
	private Dadopadraosessao dadoPadraoSessao = new Dadopadraosessao();
	private Dadopadraosessao dadoPadraoSessaoSelecionado = new Dadopadraosessao();
	private DadoPadraoSessaoDAO dadoPadraoSessaoDao = new DadoPadraoSessaoDAO();
	private List<Dadopadraosessao> listaDadoPadraoSessao;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Instrumentocoleta instrumentoColeta = new Instrumentocoleta();
	private List<Instrumentocoleta> listaInstrumentoColeta;
	private boolean ativo = false;
	
	
	// Construtor da Classe ManagedBean
	public DadoPadraoSessaoManagedBean() {
		listaDadoPadraoSessao = new ArrayList<Dadopadraosessao>();
		listaUsuarios = new ArrayList<Usuario>();
		listaInstrumentoColeta = new ArrayList<Instrumentocoleta>();
	}
	
	public Dadopadraosessao getDadoPadraoSessao() {
		return dadoPadraoSessao;
	}

	public void setDadoPadraoSessao(Dadopadraosessao dadoPadraoSessao) {
		this.dadoPadraoSessao = dadoPadraoSessao;
	}
	
	public Dadopadraosessao getDadoPadraoSessaoSelecionado() {
		return dadoPadraoSessaoSelecionado;
	}

	public void setDadoPadraoSessaoSelecionado(
			Dadopadraosessao dadoPadraoSessaoSelecionado) {
		this.dadoPadraoSessaoSelecionado = dadoPadraoSessaoSelecionado;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public List<Dadopadraosessao> getListaDadoPadraoSessao() {
		listaDadoPadraoSessao = (List<Dadopadraosessao>) dadoPadraoSessaoDao.findDadosPadroesSessaoAll(); 
		return listaDadoPadraoSessao;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll(); 
		return listaUsuarios;
	}

	public List<Instrumentocoleta> getListaInstrumentoColeta() {
		listaInstrumentoColeta = (List<Instrumentocoleta>) new br.ufscar.acumaaf.siaf.dao.InstrumentoColetaDAO().findInstrumentoColetaAll(); 
		return listaInstrumentoColeta;
	}

	//Método chamado pela página cadastrarDadoSessao.xhtml para cadastrar um novo dado da sessão de atividade física
	public String cadastrarDadoPadraoSessaoAtividadeFisica(){
		
		//Instancia com os dados selecionados
		dadoPadraoSessao.setUsuario(usuario);
		dadoPadraoSessao.setInstrumentocoleta(instrumentoColeta);
				
		System.out.println("Código.................:" + dadoPadraoSessao.getCodigodadopadraosessao());
		System.out.println("Usuário................:" + dadoPadraoSessao.getUsuario().getNome());
		System.out.println("Instrumento de coleta..:" + dadoPadraoSessao.getInstrumentocoleta().getNome());
		System.out.println("Valor Mínimo...........:" + dadoPadraoSessao.getValormin());
		System.out.println("Valor Máximo...........:" + dadoPadraoSessao.getValormax());
		System.out.println("Descrição..............:" + dadoPadraoSessao.getDescricao());
		
		//Persiste a associação no banco de dados
		dadoPadraoSessaoDao.save(dadoPadraoSessao);
		
		return "/dadopadraosessaoatividadefisica/listarDadosPadraoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		dadoPadraoSessaoDao.delete(dadoPadraoSessaoSelecionado);		
	}
	
	public void editar(){
		
		dadoPadraoSessaoDao.update(dadoPadraoSessaoSelecionado);		
	}
	
	public String chamaPaginaGerenciarDadoPadraoSessaoAtividadeFisica(){
		
		return "/dadopadraosessaoatividadefisica/gerenciaDadoPadraoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarDadoPadraoSessaoAtividadeFisica(){
		
		return "/dadopadraosessaoatividadefisica/cadastrarDadoPadraoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarDadosPadroesSessaoAtividadeFisica(){
		
		return "/dadopadraosessaoatividadefisica/listarDadosPadraoSessaoAtividadeFisica.xhtml?faces-redirect=true";
	}	
}
