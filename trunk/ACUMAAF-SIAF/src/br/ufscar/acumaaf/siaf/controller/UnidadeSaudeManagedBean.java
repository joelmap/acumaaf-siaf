package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.UnidadeSaudeDAO;
import br.ufscar.acumaaf.siaf.model.Unidadesaude;

@ManagedBean(name="unidadesaudeMB")
@ViewScoped
public class UnidadeSaudeManagedBean {
	
	private Unidadesaude unidadeSaude = new Unidadesaude();
	private UnidadeSaudeDAO unidadeSaudeDao = new UnidadeSaudeDAO();
	private List<Unidadesaude> listaUnidadeSaude;
	private Unidadesaude unidadeSaudeSelecionada = new Unidadesaude();
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public UnidadeSaudeManagedBean() {
		listaUnidadeSaude = new ArrayList<Unidadesaude>();
	}

	public Unidadesaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(Unidadesaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}
	
	public List<Unidadesaude> getListaUnidadeSaude() {
		listaUnidadeSaude = (List<Unidadesaude>) unidadeSaudeDao.findUnidadeSaudeAll();
		return listaUnidadeSaude;
	}

	public void setListaUnidadeSaude(List<Unidadesaude> listaUnidadeSaude) {
		this.listaUnidadeSaude = listaUnidadeSaude;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public Unidadesaude getUnidadeSaudeSelecionada() {
		return unidadeSaudeSelecionada;
	}

	public void setUnidadeSaudeSelecionada(Unidadesaude unidadeSaudeSelecionada) {
		this.unidadeSaudeSelecionada = unidadeSaudeSelecionada;
	}

	//Método chamado pela página cadastrarUnidadeSaude.xhtml para cadastrar uma nova Unidade de Saúde
	public String cadastrarUnidadeSaude(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "unidadesaude" do SIAF e do SMC consistentes
		unidadeSaude.setSincronizado(false);
		
			System.out.println("Código...........:" + unidadeSaude.getCodigounidadesaude());
			System.out.println("Nome.............:" + unidadeSaude.getNome());
			System.out.println("Rua..............:" + unidadeSaude.getEnderecorua());
			System.out.println("Bairro...........:" + unidadeSaude.getEnderecobairro());
			System.out.println("Cidade...........:" + unidadeSaude.getEnderecocidade());
			System.out.println("UF...............:" + unidadeSaude.getEnderecouf());
			System.out.println("CEP..............:" + unidadeSaude.getEnderecocep());
			System.out.println("E-mail...........:" + unidadeSaude.getEmail());
			System.out.println("Telefone.........:" + unidadeSaude.getTelefone());
			System.out.println("Observação.......:" + unidadeSaude.getObservacao());
			System.out.println("Sincronizado?....:" + unidadeSaude.getSincronizado());
			
			//Persiste as informações no banco de dados
			unidadeSaudeDao.save(unidadeSaude);
			
			return "/unidadesaude/listarUnidadesSaude.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um registro selecionado pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		unidadeSaudeSelecionada.setSincronizado(false);
		//Persiste as alterações no banco de dados
		unidadeSaudeDao.update(unidadeSaudeSelecionada);
	}
	
	//Método chamado para deletar um registro selecionado pelo usuário
	public void deletar(){
		unidadeSaudeDao.delete(unidadeSaudeSelecionada);
	}
	
	public String chamaPaginaCadastrarUnidadeSaude(){
		
		return "/unidadesaude/cadastrarUnidadeSaude.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarUnidadesSaude(){
		
		return "/unidadesaude/listarUnidadesSaude.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaGerenciarVinculoUsuarioUnidadesSaude(){
		
		return "/vinculousuariounidadesaude/gerenciaVinculoUsuarioUnidadeSaude.xhtml?faces-redirect=true";
	}
}