package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.EquipeSaudeFamiliaDAO;
import br.ufscar.acumaaf.siaf.model.Equipesaudefamilia;
import br.ufscar.acumaaf.siaf.model.Unidadesaude;


@ManagedBean(name="equipesaudefamiliaMB")
@ViewScoped
public class EquipeSaudeFamiliaManagedBean {
	
	private Equipesaudefamilia equipeSaudeFamilia = new Equipesaudefamilia();
	private EquipeSaudeFamiliaDAO equipeSaudeFamiliaDao = new EquipeSaudeFamiliaDAO();
	private List<Equipesaudefamilia> listaEquipeSaudeFamilia;
	private Equipesaudefamilia equipeSaudeFamiliaSelecionada = new Equipesaudefamilia();
	private Unidadesaude unidadeSaude = new Unidadesaude();
	private List<Unidadesaude> listaUnidadesSaude;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public EquipeSaudeFamiliaManagedBean() {
		listaEquipeSaudeFamilia = new ArrayList<Equipesaudefamilia>();
	}

	public Equipesaudefamilia getEquipeSaudeFamilia() {
		return equipeSaudeFamilia;
	}

	public void setEquipeSaudeFamilia(Equipesaudefamilia equipeSaudeFamilia) {
		this.equipeSaudeFamilia = equipeSaudeFamilia;
	}

	public List<Equipesaudefamilia> getListaEquipeSaudeFamilia() {
		listaEquipeSaudeFamilia = (List<Equipesaudefamilia>) equipeSaudeFamiliaDao.findEquipeSaudeFamiliaAll();
		return listaEquipeSaudeFamilia;
	}

	public void setListaEquipeSaudeFamilia(	List<Equipesaudefamilia> listaEquipeSaudeFamilia) {
		this.listaEquipeSaudeFamilia = listaEquipeSaudeFamilia;
	}

	public Equipesaudefamilia getEquipeSaudeFamiliaSelecionada() {
		return equipeSaudeFamiliaSelecionada;
	}

	public void setEquipeSaudeFamiliaSelecionada(
			Equipesaudefamilia equipeSaudeFamiliaSelecionada) {
		this.equipeSaudeFamiliaSelecionada = equipeSaudeFamiliaSelecionada;
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

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método chamado pela página cadastrarEquipeSaudeFamilia.xhtml para cadastrar uma nova Equipe de Saúde da Família
	public String cadastrarEquipeSaudeFamilia(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "equipesaudefamilia" do SIAF e do SMC consistentes
		equipeSaudeFamilia.setSincronizado(false);
		
		//Instancia o objeto EquipeSaudeFamilia com os dados selecionados
		equipeSaudeFamilia.setUnidadesaude(unidadeSaude);
		
			System.out.println("Código...........:" + equipeSaudeFamilia.getCodigoequipesaudefamilia());
			System.out.println("Nome.............:" + equipeSaudeFamilia.getNome());
			System.out.println("Unidade de Saúde.:" + equipeSaudeFamilia.getUnidadesaude().getCodigounidadesaude());
			System.out.println("Descrição........:" + equipeSaudeFamilia.getDescricao());
			System.out.println("Sincronizado?....:" + equipeSaudeFamilia.getSincronizado());
			
			//Periste as informações no banco de dados
			equipeSaudeFamiliaDao.save(equipeSaudeFamilia);
			
			return "/equipesaudefamilia/listarEquipesSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um registro selecionado pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		equipeSaudeFamiliaSelecionada.setSincronizado(false);
		//Persite as alterações realizadas pelo usuário
		equipeSaudeFamiliaDao.update(equipeSaudeFamiliaSelecionada);
	}
	
	//Método chamado para deletar um registro selecionado pelo usuário
	public void deletar(){
		equipeSaudeFamiliaDao.delete(equipeSaudeFamiliaSelecionada);
	}
	
	public String chamaPaginaGerenciarEquipeSaudeFamilia(){
		
		return "/equipesaudefamilia/gerenciaEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaCadastrarEquipeSaudeFamilia(){
		
		return "/equipesaudefamilia/cadastrarEquipeSaudeFamilia.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarEquipesSaudeFamilia(){
		
		return "/equipesaudefamilia/listarEquipesSaudeFamilia.xhtml?faces-redirect=true";
	}
}