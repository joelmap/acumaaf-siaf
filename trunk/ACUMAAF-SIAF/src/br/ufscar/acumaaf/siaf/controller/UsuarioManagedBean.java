package br.ufscar.acumaaf.siaf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import br.ufscar.acumaaf.siaf.dao.UsuarioDAO;
import br.ufscar.acumaaf.siaf.model.Usuario;

@ManagedBean(name="usuarioMB")
@ViewScoped
public class UsuarioManagedBean {

	private Usuario usuario = new Usuario();
	private UsuarioDAO usuarioDao = new UsuarioDAO();
	private List<Usuario> listaUsuarios;
	private Usuario usuarioSelecionado = new Usuario();
	private boolean ativo = false;
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	Date data;
	
	// Construtor da Classe ManagedBean
	public UsuarioManagedBean() {
		listaUsuarios = new ArrayList<Usuario>();
		//Pega a data atual do sistema
		data = new Date();
		try {
			data = (java.util.Date) formatoData.parse(formatoData.format(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		usuario.setDatacadastro(data);
		usuario.setDatamodificacao(data);
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	//Obtem a lista de todos os usuários cadastrados no banco de dados
	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) usuarioDao.findUserAll();
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
	
	public void uploadFoto(FileUploadEvent event){
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + "is uploaded!");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	//Método chamado pela página cadastrarUsuario.xhtml para cadastrar um novo usuário
	public String cadastrarUsuario(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "usuario" do SIAF e do SMC consistentes
		usuario.setSincronizado(false);
		
			System.out.println("Código...........:" + usuario.getCodigousuario());
			System.out.println("Nome.............:" + usuario.getNome());
			System.out.println("Foto.............:" + usuario.getFoto());
			System.out.println("Sexo.............:" + usuario.getSexo());
			System.out.println("Data Nascimento..:" + usuario.getDatanascimento());
			System.out.println("Tipo de Documento:" + usuario.getTipodocumento());
			System.out.println("Número Documento.:" + usuario.getNumerodocumento());
			System.out.println("Rua..............:" + usuario.getEnderecorua());
			System.out.println("Bairro...........:" + usuario.getEnderecobairro());
			System.out.println("Cidade...........:" + usuario.getEnderecocidade());
			System.out.println("UF...............:" + usuario.getEnderecouf());
			System.out.println("CEP..............:" + usuario.getEnderecocep());
			System.out.println("E-mail...........:" + usuario.getEmail());
			System.out.println("Telefone.........:" + usuario.getTelefone());
			System.out.println("Usuario..........:" + usuario.getUsuario());
			System.out.println("Senha............:" + usuario.getSenha());
			System.out.println("Tipo Usuario.....:" + usuario.getTipousuario());
			System.out.println("Data Cadastro....:" + usuario.getDatacadastro());
			System.out.println("Data Atualização.:" + usuario.getDatamodificacao());
			System.out.println("Usuário modificou:" + usuario.getUsuariomodificacao());
			System.out.println("Sincronizado?....:" + usuario.getSincronizado());
						
		usuarioDao.save(usuario);
		return "/usuario/listarUsuarios.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um registro selecionado pelo usuário
	public void editar(){
		
		System.out.println("EDITAR - Código...........:" + usuarioSelecionado.getCodigousuario());
		System.out.println("EDITAR - Nome.............:" + usuarioSelecionado.getNome());
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		usuarioSelecionado.setSincronizado(false);		
		//Persiste as informações no banco de dados
		usuarioDao.update(usuarioSelecionado);
	}
	
	//Método chamado para deletar um registro selecionado pelo usuário
	public void deletar(){
				
		usuarioDao.delete(usuarioSelecionado);
	}
	
	//Método para realizar a navegação implícita
	public String chamaPaginaCadastrarUsuario(){
		
		return "/usuario/cadastrarUsuario.xhtml?faces-redirect=true";
	}
	
	//Método para realizar a navegação implícita
	public String chamaPaginaListarUsuarios(){
		
		return "/usuario/listarUsuarios.xhtml?faces-redirect=true";
	}
}
