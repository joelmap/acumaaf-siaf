package br.ufscar.acumaaf.siaf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.MsnUsuarioDAO;
import br.ufscar.acumaaf.siaf.model.Msnusuario;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="msnMB")
@ViewScoped
public class MsnManagedBean {
	
	private Msnusuario msn = new Msnusuario();
	private Msnusuario msnSelecionado = new Msnusuario();
	private List<Msnusuario> listaMsnNaoLida;
	private List<Msnusuario> listaMsnLida;
	
	private MsnUsuarioDAO msnDao = new MsnUsuarioDAO();
	
	private Usuario usuarioOrigem = new Usuario();
	private Usuario usuarioDestino = new Usuario();
	private List<Usuario> listaUsuarios;
	private Usuario usuario = new Usuario();
	
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	Date data;
	
	private boolean mostrarNovas = false;
	private boolean mostrarLidas = false;
	private boolean criarEmail = true;
		
	// Construtor da Classe ManagedBean
	public MsnManagedBean() {
		listaMsnNaoLida = new ArrayList<Msnusuario>();
		listaMsnLida = new ArrayList<Msnusuario>();
		listaUsuarios = new ArrayList<Usuario>();
		
		//Pega a data atual do sistema
		data = new Date();
		try {
			data = (java.util.Date) formatoData.parse(formatoData.format(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		msn.setData(data);
	}
	
	public Msnusuario getMsn() {
		return msn;
	}

	public void setMsn(Msnusuario msn) {
		this.msn = msn;
	}

	public Msnusuario getMsnSelecionado() {
		return msnSelecionado;
	}

	public void setMsnSelecionado(Msnusuario msnSelecionado) {
		this.msnSelecionado = msnSelecionado;
	}

	public Usuario getUsuarioOrigem() {
		return usuarioOrigem;
	}

	public void setUsuarioOrigem(Usuario usuarioOrigem) {
		this.usuarioOrigem = usuarioOrigem;
	}

	public Usuario getUsuarioDestino() {
		return usuarioDestino;
	}

	public void setUsuarioDestino(Usuario usuarioDestino) {
		this.usuarioDestino = usuarioDestino;
	}

	public List<Msnusuario> getListaMsnNaoLida() {
		listaMsnNaoLida = (List<Msnusuario>) msnDao.findMsnNaoLida(usuario.getCodigousuario());
		return listaMsnNaoLida;
	}
	
	public List<Msnusuario> getListaMsnLida() {
		listaMsnLida = (List<Msnusuario>) msnDao.findMsnLida(usuario.getCodigousuario());
		return listaMsnLida;
	}

	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll(); 
		return listaUsuarios;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public boolean isMostrarNovas() {
		return mostrarNovas;
	}

	public void setMostrarNovas(boolean mostrarNovas) {
		this.mostrarNovas = mostrarNovas;
	}

	public boolean isMostrarLidas() {
		return mostrarLidas;
	}

	public void setMostrarLidas(boolean mostrarLidas) {
		this.mostrarLidas = mostrarLidas;
	}
	
	public boolean isCriarEmail() {
		return criarEmail;
	}

	public void setCriarEmail(boolean criarEmail) {
		this.criarEmail = criarEmail;
	}

	//Método chamado pela página cadastrarMsn.xhtml para cadastrar uma nova mensagem
	public String enviarMensagem(){
		
			//Instancia com os dados selecionados
			msn.setUsuario1(usuarioOrigem);
			msn.setUsuario2(usuarioDestino);
			//"false" significa que a mensagem ainda não foi lida
			msn.setStatusmensagem(false);
					
			System.out.println("Código.................:" + msn.getCodigomsnusuario());
			System.out.println("Usuário Origem.........:" + msn.getUsuario1());
			System.out.println("Usuário Destino........:" + msn.getUsuario2());
			System.out.println("Titulo da Mensagem.....:" + msn.getTitulo());
			System.out.println("Conteudo Mensagem......:" + msn.getMensagem());
			System.out.println("Data de encio..........:" + msn.getData());
			System.out.println("Mensagem foi lida?.....:" + msn.getStatusmensagem());
			
			//Persiste a mensagem no banco de dados
			msnDao.save(msn);
			
			return "/msn/gerenciaMsn.xhtml?faces-redirect=true";		
	}
	
	public void deletar(){
		
		msnDao.delete(msnSelecionado);		
	}
	
	public void lida(){
		
		msnDao.update(msnSelecionado);		
	}
	
	public String chamaPaginaGerenciarMsn(){
		
		return "/msn/gerenciaMsn.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaEnviarMensagem(){
		
		return "/msn/cadastrarMsn.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarMsnLidas(){
		
		return "/msn/listaMsnLidas.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarMsnNovas(){
		
		return "/msn/listaMsnNovas.xhtml?faces-redirect=true";
	}
	
	public void teste(){
		mostrarLidas = true;
		System.out.println("Valor de mostrarLidas: " + mostrarLidas);
	}
	
}
