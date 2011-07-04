package br.ufscar.acumaaf.siaf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.InstrumentoColetaDAO;
import br.ufscar.acumaaf.siaf.model.Instrumentocoleta;


@ManagedBean(name="instrumentocoletaMB")
@ViewScoped
public class InstrumentoColetaManagedBean {
	
	private Instrumentocoleta instrumentoColeta = new Instrumentocoleta();
	private Instrumentocoleta instrumentoColetaSelecionado = new Instrumentocoleta();
	private InstrumentoColetaDAO instrumentoColetaDao = new InstrumentoColetaDAO();
	private List<Instrumentocoleta> listaInstrumentoColeta;
	private boolean ativo = false;
	
	// Construtor da Classe ManagedBean
	public InstrumentoColetaManagedBean() {
		listaInstrumentoColeta = new ArrayList<Instrumentocoleta>();
	}
	
	public Instrumentocoleta getInstrumentoColeta() {
		return instrumentoColeta;
	}

	public void setInstrumentoColeta(Instrumentocoleta instrumentoColeta) {
		this.instrumentoColeta = instrumentoColeta;
	}

	public Instrumentocoleta getInstrumentoColetaSelecionado() {
		return instrumentoColetaSelecionado;
	}

	public void setInstrumentoColetaSelecionado(
			Instrumentocoleta instrumentoColetaSelecionado) {
		this.instrumentoColetaSelecionado = instrumentoColetaSelecionado;
	}

	public List<Instrumentocoleta> getListaInstrumentoColeta() {
		listaInstrumentoColeta = (List<Instrumentocoleta>) instrumentoColetaDao.findInstrumentoColetaAll();
		return listaInstrumentoColeta;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	//Método para cadastrar uma novo Instrumento de Coleta
	public String cadastrarInstrumentoColeta(){
		
		//Sempre configura o cadastro e as atualizações para não sincronizado (false). 
		//Desta forma o SMC sincronizará os dados e manterá as tabelas "instrumentocoleta" do SIAF e do SMC consistentes
		instrumentoColeta.setSincronizado(false);
		
		//System.out.println("Código...........:" + instrumentoColeta.getCodigoinstrumentocoleta());
		//System.out.println("Nome.............:" + instrumentoColeta.getNome());
		//System.out.println("Unidade de Medida:" + instrumentoColeta.getUnidademedida());
		//System.out.println("Descrição........:" + instrumentoColeta.getDescricao());
		//System.out.println("Sincronizado?....:" + instrumentoColeta.getSincronizado());
		
		//Persiste as informações no banco de dados
		instrumentoColetaDao.save(instrumentoColeta);
		
		return "/instrumentocoleta/listarInstrumentosColeta.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um Instrumento de Coleta selecionado pelo usuário
	public void editar(){
		
		//Muda o atributo "sincronizado" para falso para que o SMC sincronize as informações atualizadas pelo usuário
		instrumentoColetaSelecionado.setSincronizado(false);
		//Persiste as alterações no banco de dados
		instrumentoColetaDao.update(instrumentoColetaSelecionado);
	
	}
	
	//Método chamado para deletar um Instrumento de Coleta selecionado pelo usuário
	public void deletar(){
		
		instrumentoColetaDao.delete(instrumentoColetaSelecionado);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastro de Instrumento de Coleta
	public String chamaPaginaCadastrarInstrumentoColeta(){
		
		return "/instrumentocoleta/cadastrarInstrumentoColeta.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que lista todos os Instrumento de Coleta cadastrados
	public String chamaPaginaListarInstrumentosColeta(){
		
		return "/instrumentocoleta/listarInstrumentosColeta.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que gerencia os registros de Instrumento de Coleta
	public String chamaPaginaGerenciarInstrumentoColeta(){
		
		return "/instrumentocoleta/gerenciaInstrumentoColeta.xhtml?faces-redirect=true";
	}
}
