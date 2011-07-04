package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.VinculoUsuarioUnidadeDeSaudeDAO;
import br.ufscar.acumaaf.siaf.model.Unidadesaude;
import br.ufscar.acumaaf.siaf.model.Usuario;
import br.ufscar.acumaaf.siaf.model.Vinculousuariounidadedesaude;

@ManagedBean(name="vinculousuariounidadesaudeMB")
@ViewScoped
public class VinculoUsuarioUnidadeDeSaudeManagedBean {
	
	private Vinculousuariounidadedesaude vinculoUsuarioUnidadeSaude = new Vinculousuariounidadedesaude();
	private VinculoUsuarioUnidadeDeSaudeDAO vinculoUsuarioUnidadeSaudeDAO = new VinculoUsuarioUnidadeDeSaudeDAO();
	private List<Vinculousuariounidadedesaude> listaVinculoUsuarioUnidadeSaude;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private Unidadesaude unidadeSaude = new Unidadesaude();
	private List<Unidadesaude> listaUnidadesSaude;
	private Vinculousuariounidadedesaude vinculoUsuarioUnidadeSaudeSelcecionado = new Vinculousuariounidadedesaude();
	
	// Construtor da Classe ManagedBean
	public VinculoUsuarioUnidadeDeSaudeManagedBean() {
		listaVinculoUsuarioUnidadeSaude = new ArrayList<Vinculousuariounidadedesaude>();
	}

	public Vinculousuariounidadedesaude getVinculoUsuarioUnidadeSaude() {
		return vinculoUsuarioUnidadeSaude;
	}

	public void setVinculoUsuarioUnidadeSaude(Vinculousuariounidadedesaude vinculoUsuarioUnidadeSaude) {
		this.vinculoUsuarioUnidadeSaude = vinculoUsuarioUnidadeSaude;
	}

	public List<Vinculousuariounidadedesaude> getListaVinculoUsuarioUnidadeSaude() {
		listaVinculoUsuarioUnidadeSaude = vinculoUsuarioUnidadeSaudeDAO.findVinculoUsuarioUnidadeSaudeAll();
		return listaVinculoUsuarioUnidadeSaude;
	}

	public void setListaVinculoUsuarioUnidadeSaude(List<Vinculousuariounidadedesaude> listaVinculoUsuarioUnidadeSaude) {
		this.listaVinculoUsuarioUnidadeSaude = listaVinculoUsuarioUnidadeSaude;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Usuario> getListaUsuarios() {
		listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUserAll();
		return listaUsuarios;
	}

	public Unidadesaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(Unidadesaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}
	
	public List<Unidadesaude> getListaUnidadesSaude() {
		listaUnidadesSaude = (List<Unidadesaude>) new br.ufscar.acumaaf.siaf.dao.UnidadeSaudeDAO().findUnidadeSaudeAll();
		return listaUnidadesSaude;
	}
	
	public Vinculousuariounidadedesaude getVinculoUsuarioUnidadeSaudeSelcecionado() {
		return vinculoUsuarioUnidadeSaudeSelcecionado;
	}

	public void setVinculoUsuarioUnidadeSaudeSelcecionado(
			Vinculousuariounidadedesaude vinculoUsuarioUnidadeSaudeSelcecionado) {
		this.vinculoUsuarioUnidadeSaudeSelcecionado = vinculoUsuarioUnidadeSaudeSelcecionado;
	}

	//Método chamado pela página cadastrarVinculoUsuarioUnidadeSaude.xhtml para cadastrar um novo vínculo entre usuário e a unidade de saúde
	public String cadastrarVinculoUsuarioUnidadeSaude(){
		
		//Procura o usuário que foi selecionado no combobox no banco de dados e instancia um objeto Usuario 
		//usuario = usuarioDAO.findUserByCode(usuario.getCodigousuario());
		System.out.println("Codigo: " + usuario.getCodigousuario());
		System.out.println("Nome..: " + usuario.getNome());
		
		//Procura a unidade de saúde que foi selecionado no combobox no banco de dados e instancia um objeto Unidadesaude
		//unidadeSaude = unidadeSaudeDAO.findUnidadeSaudeByCode(codigoUnidadeSaude);
		System.out.println("Codigo: " + unidadeSaude.getCodigounidadesaude());
		System.out.println("Nome..: " + unidadeSaude.getNome());
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "vinculousuariounidadesaude" do SIAF e do SMC consistentes
		vinculoUsuarioUnidadeSaude.setSincronizado(false);
		
		//Instancia com os dados selecionados
		vinculoUsuarioUnidadeSaude.setUsuario(usuario);
		vinculoUsuarioUnidadeSaude.setUnidadesaude(unidadeSaude);
		
		System.out.println("Código.................:" + vinculoUsuarioUnidadeSaude.getCodigovinculo());
		System.out.println("Código Usuário.........:" + vinculoUsuarioUnidadeSaude.getUsuario());
		System.out.println("Código Unidade de Saúde:" + vinculoUsuarioUnidadeSaude.getUnidadesaude());
		System.out.println("Sincronizado?..........:" + vinculoUsuarioUnidadeSaude.getSincronizado());
		
		//Persiste a associação no banco de dados
		vinculoUsuarioUnidadeSaudeDAO.save(vinculoUsuarioUnidadeSaude);
		
		return "/vinculousuariounidadesaude/listarVinculosUsuarioUnidadeSaude.xhtml?faces-redirect=true";
	}
	
	public void deletar(){
		
		System.out.println("Código.................:" + vinculoUsuarioUnidadeSaudeSelcecionado.getCodigovinculo());
		System.out.println("Código Usuário.........:" + vinculoUsuarioUnidadeSaudeSelcecionado.getUsuario());
		System.out.println("Código Unidade de Saúde:" + vinculoUsuarioUnidadeSaudeSelcecionado.getUnidadesaude());
		System.out.println("Sincronizado?..........:" + vinculoUsuarioUnidadeSaudeSelcecionado.getSincronizado());
		
		vinculoUsuarioUnidadeSaudeDAO.delete(vinculoUsuarioUnidadeSaudeSelcecionado);		
	}
	
	public String chamaPaginaCadastrarVinculoUsuarioUnidadeSaude(){
		
		return "/vinculousuariounidadesaude/cadastrarVinculoUsuarioUnidadeSaude.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarVinculosUsuarioUnidadeSaude(){
		
		return "/vinculousuariounidadesaude/listarVinculosUsuarioUnidadeSaude.xhtml?faces-redirect=true";
	}
	
}
