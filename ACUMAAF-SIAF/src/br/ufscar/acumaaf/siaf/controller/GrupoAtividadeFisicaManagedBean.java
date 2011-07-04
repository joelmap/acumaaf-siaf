package br.ufscar.acumaaf.siaf.controller;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufscar.acumaaf.siaf.dao.GrupoAtividadeFisicaDAO;
import br.ufscar.acumaaf.siaf.model.Atividadefisica;
import br.ufscar.acumaaf.siaf.model.Diasemana;
import br.ufscar.acumaaf.siaf.model.Equipesaudefamilia;
import br.ufscar.acumaaf.siaf.model.Grupoatividadefisica;
import br.ufscar.acumaaf.siaf.model.Periodicidade;
import br.ufscar.acumaaf.siaf.model.Unidadesaude;


@ManagedBean(name="grupoatividadefisicaMB")
@ViewScoped
public class GrupoAtividadeFisicaManagedBean {
	
	private Grupoatividadefisica grupoAtividadeFisica = new Grupoatividadefisica();
	private Grupoatividadefisica grupoAtividadeFisicaSelecionado = new Grupoatividadefisica();
	private GrupoAtividadeFisicaDAO grupoAtividadeFisicaDao = new GrupoAtividadeFisicaDAO();
	private List<Grupoatividadefisica> listaGrupoAtividadeFisica;
	private Unidadesaude unidadeSaude = new Unidadesaude();
	private List<Unidadesaude> listaUnidadeSaude;
	private Equipesaudefamilia equipeSaudeFamilia = new Equipesaudefamilia();
	private List<Equipesaudefamilia> listaEquipeSaudeFamilia;
	private Atividadefisica atividadeFisica = new Atividadefisica();
	private List<Atividadefisica> listaAtividadeFisica;
	private Periodicidade periodicidade = new Periodicidade();
	private List<Periodicidade> listaPeriodicidade;
	private Diasemana diaSemana = new Diasemana();
	private List<Diasemana> listaDiaSemana;
	private boolean ativo = false;
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
	Date data;
	Date hora;
	String horaFim;	
	String horaInicio;
	
	// Construtor da Classe ManagedBean
	public GrupoAtividadeFisicaManagedBean() {
		listaGrupoAtividadeFisica = new ArrayList<Grupoatividadefisica>();
		listaUnidadeSaude = new ArrayList<Unidadesaude>();
		listaEquipeSaudeFamilia = new ArrayList<Equipesaudefamilia>();
		listaAtividadeFisica = new ArrayList<Atividadefisica>();
		listaPeriodicidade = new ArrayList<Periodicidade>();
		listaDiaSemana = new ArrayList<Diasemana>();
		
		//PEGA A DATA DO SISTEMA PARA INSTANCIAR DATA DO CADASTRO/CRIAÇÃO 
		data = new Date();
		try {
			data = (java.util.Date) formatoData.parse(formatoData.format(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		grupoAtividadeFisica.setDatacriacao(data);
	}
	
	public Grupoatividadefisica getGrupoAtividadeFisica() {
		return grupoAtividadeFisica;
	}

	public void setGrupoAtividadeFisica(Grupoatividadefisica grupoAtividadeFisica) {
		this.grupoAtividadeFisica = grupoAtividadeFisica;
	}

	public Grupoatividadefisica getGrupoAtividadeFisicaSelecionado() {
		return grupoAtividadeFisicaSelecionado;
	}

	public void setGrupoAtividadeFisicaSelecionado(
			Grupoatividadefisica grupoAtividadeFisicaSelecionado) {
		this.grupoAtividadeFisicaSelecionado = grupoAtividadeFisicaSelecionado;
	}

	public List<Grupoatividadefisica> getListaGrupoAtividadeFisica() {
		listaGrupoAtividadeFisica = (List<Grupoatividadefisica>) grupoAtividadeFisicaDao.findGrupoAtividadeFisicaAll();
		return listaGrupoAtividadeFisica;
	}
	
	public Unidadesaude getUnidadeSaude() {
		return unidadeSaude;
	}

	public void setUnidadeSaude(Unidadesaude unidadeSaude) {
		this.unidadeSaude = unidadeSaude;
	}

	public List<Unidadesaude> getListaUnidadeSaude() {
		listaUnidadeSaude = (List<Unidadesaude>) new br.ufscar.acumaaf.siaf.dao.UnidadeSaudeDAO().findUnidadeSaudeAll(); 
		return listaUnidadeSaude;
	}
	
	public Equipesaudefamilia getEquipeSaudeFamilia() {
		return equipeSaudeFamilia;
	}

	public void setEquipeSaudeFamilia(Equipesaudefamilia equipeSaudeFamilia) {
		this.equipeSaudeFamilia = equipeSaudeFamilia;
	}

	public List<Equipesaudefamilia> getListaEquipeSaudeFamilia() {
		listaEquipeSaudeFamilia = (List<Equipesaudefamilia>) new br.ufscar.acumaaf.siaf.dao.EquipeSaudeFamiliaDAO().findEquipeSaudeFamiliaAll();
		return listaEquipeSaudeFamilia;
	}
	
	public Atividadefisica getAtividadeFisica() {
		return atividadeFisica;
	}

	public void setAtividadeFisica(Atividadefisica atividadeFisica) {
		this.atividadeFisica = atividadeFisica;
	}

	public Periodicidade getPeriodicidade() {
		return periodicidade;
	}

	public void setPeriodicidade(Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}

	public List<Atividadefisica> getListaAtividadeFisica() {
		listaAtividadeFisica = (List<Atividadefisica>) new br.ufscar.acumaaf.siaf.dao.AtividadeFisicaDAO().findAtividadeFisicaAll(); 
		return listaAtividadeFisica;
	}

	public List<Periodicidade> getListaPeriodicidade() {
		listaPeriodicidade = (List<Periodicidade>) new br.ufscar.acumaaf.siaf.dao.PeriodicidadeDAO().findPeriodicidadeAll();
		return listaPeriodicidade;
	}
	
	public Diasemana getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Diasemana diaSemana) {
		this.diaSemana = diaSemana;
	}

	public List<Diasemana> getListaDiaSemana() {
		listaDiaSemana = (List<Diasemana>) new br.ufscar.acumaaf.siaf.dao.DiaSemanaDAO().findDiaSemanaAll(); 
		return listaDiaSemana;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	public String getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(String horaFim) {
		this.horaFim = horaFim;
	}
	
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	//Método chamado pela página cadastrarGrupoAtividadeFisica.xhtml para cadastrar um novo Grupo de Atividade Física
	public String cadastrarGrupoAtividadeFisica(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "grupoatividadefisica" do SIAF e do SMC consistentes
		grupoAtividadeFisica.setSincronizado(false);
		
		//(ativo) verifica se o usuário selecionou alguma frequência para o grupo de atividade física
		if(ativo){
			
			//Instancia com os dados selecionados na página cadastrarGrupoAtividadeFisica.xhtml
			grupoAtividadeFisica.setUnidadesaude(unidadeSaude);
			grupoAtividadeFisica.setEquipesaudefamilia(equipeSaudeFamilia);
			grupoAtividadeFisica.setAtividadefisica(atividadeFisica);
			grupoAtividadeFisica.setPeriodicidade(periodicidade);
			grupoAtividadeFisica.setDiasemana(diaSemana);
			grupoAtividadeFisica.setStatusgrupoatividadefisica(true);
			
			//Formata a hora recebida do combobox para o tipo TIME do SQL
			try {
				hora = formatoHora.parse(horaInicio);				
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			grupoAtividadeFisica.setHorainicio(new Time(hora.getTime()));
			
			try {
				hora = formatoHora.parse(horaFim);				
			} catch (ParseException e) {
				e.printStackTrace();
			}  
			grupoAtividadeFisica.setHorafim(new Time(hora.getTime()));
			
			System.out.println("Código..........................:" + grupoAtividadeFisica.getCodigogrupoatividadefisica());
			System.out.println("Nome............................:" + grupoAtividadeFisica.getNome());
			System.out.println("Unidade de Saúde................:" + grupoAtividadeFisica.getUnidadesaude().getNome());
			System.out.println("Equipe de Saúde da Família......:" + grupoAtividadeFisica.getEquipesaudefamilia().getNome());
			System.out.println("Atividade Física................:" + grupoAtividadeFisica.getAtividadefisica().getNome());
			System.out.println("Periodicidade...................:" + grupoAtividadeFisica.getPeriodicidade().getNome());
			System.out.println("Codigo da Frequência/dia semana):" + grupoAtividadeFisica.getDiasemana().getCodigodiasemana());
			System.out.println("Hora de Início das Atividades...:" + grupoAtividadeFisica.getHorainicio());
			System.out.println("Hora de Término das Atividades..:" + grupoAtividadeFisica.getHorafim());
			System.out.println("Data de Criação do Grupo........:" + grupoAtividadeFisica.getDatacriacao());
			System.out.println("Data de Início do Grupo.........:" + grupoAtividadeFisica.getDatainicio());
			System.out.println("Local realização das Atividades.:" + grupoAtividadeFisica.getLocalatividade());
			System.out.println("Observação......................:" + grupoAtividadeFisica.getObservacao());
			System.out.println("Status do Grupo (Ativo).........:" + grupoAtividadeFisica.getStatusgrupoatividadefisica());
			System.out.println("Data de Encerramento do Grupo...:" + grupoAtividadeFisica.getDataencerramento());
			System.out.println("Sincronizado?...................:" + grupoAtividadeFisica.getSincronizado());
			
			//Persiste o cadastro no banco de dados
			grupoAtividadeFisicaDao.save(grupoAtividadeFisica);
			return "/grupoatividadefisica/listarGruposAtividadeFisica.xhtml?faces-redirect=true";
		}
		else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "[Atenção!] É necessário selecionar os dias da semana em que a atividade será realizada. Faça uma escolha no campo de Dados da Frequência da Atividade Física!.", "."));
			return "#";
		}	
	}
	
	public void editar(){
		
		//Formata a hora recebida do combobox para o tipo TIME do SQL
		try {
			hora = formatoHora.parse(horaInicio);				
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		grupoAtividadeFisicaSelecionado.setHorainicio(new Time(hora.getTime()));
		
		try {
			hora = formatoHora.parse(horaFim);				
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		grupoAtividadeFisicaSelecionado.setHorafim(new Time(hora.getTime()));
		
		//Atualiza determinado registro no banco de dados
		grupoAtividadeFisicaDao.update(grupoAtividadeFisicaSelecionado);
	}
	
	public void deletar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		grupoAtividadeFisicaSelecionado.setSincronizado(false);
		//Deleta um registro selecionado pelo usuário
		grupoAtividadeFisicaDao.delete(grupoAtividadeFisicaSelecionado);		
	}
	
	public String chamaPaginaCadastrarGrupoAtividadeFisica(){
		
		return "/grupoatividadefisica/cadastrarGrupoAtividadeFisica.xhtml?faces-redirect=true";
	}
	
	public String chamaPaginaListarGruposAtividadeFisica(){
		
		return "/grupoatividadefisica/listarGruposAtividadeFisica.xhtml?faces-redirect=true";
	}	
}