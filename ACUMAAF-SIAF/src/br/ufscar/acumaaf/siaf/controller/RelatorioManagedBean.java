package br.ufscar.acumaaf.siaf.controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.chart.series.ChartSeries;
import org.primefaces.model.chart.CartesianChartModel;

import br.ufscar.acumaaf.siaf.model.Dadosessao;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="relatorioMB")
@ViewScoped
public class RelatorioManagedBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private CartesianChartModel indicadorPeso;
	private CartesianChartModel indicadorPressaoArterial;
	
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	private boolean mostrar = false;
	
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	
	// Construtor da Classe ManagedBean
	public RelatorioManagedBean() {
		listaUsuarios = new ArrayList<Usuario>();
	}
	
	//Método que gera o relatório dos pesos do participante escolhido pelo usuário
	public CartesianChartModel getIndicadorPeso() {
		indicadorPeso = new CartesianChartModel();
		ChartSeries pesos = new ChartSeries();
		ChartSeries pesoMax = new ChartSeries();
//		ChartSeries pesoMax2 = new ChartSeries();
		ChartSeries pesoMin = new ChartSeries();
		
		
		pesos.setLabel("Peso do Participante(kg)");
		pesoMax.setLabel("Peso de controle Máximo");
		pesoMin.setLabel("Peso de controle Mínimo");
		
		for (Dadosessao dado : (List<Dadosessao>) new br.ufscar.acumaaf.siaf.dao.DadoSessaoDAO().findDadoPesoByUsuario(usuario.getCodigousuario())) {
			pesos.set(formatoData.format(dado.getSessao().getData()).toString(),Integer.parseInt(dado.getValor()));
			pesoMax.set(formatoData.format(dado.getSessao().getData()).toString(), 80);
			pesoMin.set(formatoData.format(dado.getSessao().getData()).toString(), 40);
		}
		
//		pesoMax.set("30/05/2011", 77);
//		pesoMax2.set("30/05/2011", 77);
//		pesoMin.set("30/05/2011", 57);
//		pesos.set("30/05/2011",76);
//		pesoMax.set("01/06/2011", 77);
//		pesoMax2.set("01/06/2011", 77);
//		pesoMin.set("01/06/2011", 57);
//		pesos.set("01/06/2011",75.2);
//		pesoMax.set("03/06/2011", 77);
//		pesoMax2.set("03/06/2011", 77);
//		pesoMin.set("03/06/2011", 57);
//		pesos.set("03/06/2011",74.1);
//		pesoMax.set("06/06/2011", 77);
//		pesoMax2.set("06/06/2011", 77);
//		pesoMin.set("06/06/2011", 57);
//		pesos.set("06/06/2011",73.5);
//		pesoMax.set("08/06/2011", 77);
//		pesoMax2.set("08/06/2011", 77);
//		pesoMin.set("08/06/2011", 57);
//		pesos.set("08/06/2011",72);
//		pesoMax.set("10/06/2011", 77);
//		pesoMax2.set("10/06/2011", 77);
//		pesoMin.set("10/06/2011", 57);
//		pesos.set("10/06/2011",71.5);
//		pesoMax.set("13/06/2011", 77);
//		pesoMax2.set("13/06/2011", 77);
//		pesoMin.set("13/06/2011", 57);
//		pesos.set("13/06/2011",71);
//		pesoMax.set("15/06/2011", 77);
//		pesoMax2.set("15/06/2011", 77);
//		pesoMin.set("15/06/2011", 57);
//		pesos.set("15/06/2011",70);

		
		
		
		indicadorPeso.addSeries(pesoMax);
		indicadorPeso.addSeries(pesos);
		indicadorPeso.addSeries(pesoMin);
//		indicadorPeso.addSeries(pesoMax2);

		
		return indicadorPeso;
	}
	
	//Método que gera o relatório das pressões arteriais do participante escolhido pelo usuário
	public CartesianChartModel getIndicadorPressaoArterial() {
		indicadorPressaoArterial = new CartesianChartModel();
		ChartSeries pressaoSistolicaPadrao = new ChartSeries();
		ChartSeries pressaoDiastolicaPadrao = new ChartSeries();
		ChartSeries pressaoSistolicaPaciente = new ChartSeries();
		ChartSeries pressaoDiastolicaPaciente = new ChartSeries();
		
		pressaoSistolicaPadrao.setLabel("Pressão Sistólica Padrão"); 
		pressaoDiastolicaPadrao.setLabel("Pressão Diastólica Padrão");
		pressaoSistolicaPaciente.setLabel("Pressão Sistólica do Paciente");
		pressaoDiastolicaPaciente.setLabel("Pressão Diastólica do Paciente");
		
		for (Dadosessao dado : (List<Dadosessao>) new br.ufscar.acumaaf.siaf.dao.DadoSessaoDAO().findDadoPressaoArterialByUsuario(usuario.getCodigousuario())) {
			
			pressaoSistolicaPadrao.set(formatoData.format(dado.getSessao().getData()).toString(),120);
			pressaoSistolicaPaciente.set(formatoData.format(dado.getSessao().getData()).toString(), Integer.valueOf(dado.getValor().substring(0,dado.getValor().indexOf("x"))));
			pressaoDiastolicaPadrao.set(formatoData.format(dado.getSessao().getData()).toString(),80);
			pressaoDiastolicaPaciente.set(formatoData.format(dado.getSessao().getData()).toString(),Integer.valueOf(dado.getValor().substring(dado.getValor().indexOf("x") + 1)));
		}
		
	

//		pressaoSistolicaPadrao.set("09:00",120);
//		pressaoSistolicaPaciente.set("09:00",123);
//		pressaoDiastolicaPadrao.set("09:00",80);
//		pressaoDiastolicaPaciente.set("09:00",81);
//		pressaoSistolicaPadrao.set("09:10",120);
//		pressaoSistolicaPaciente.set("09:10",125);
//		pressaoDiastolicaPadrao.set("09:10",80);
//		pressaoDiastolicaPaciente.set("09:10",86);
//		pressaoSistolicaPadrao.set("09:20",120);
//		pressaoSistolicaPaciente.set("09:20",126);
//		pressaoDiastolicaPadrao.set("09:20",80);
//		pressaoDiastolicaPaciente.set("09:20",86);
//		pressaoSistolicaPadrao.set("09:30",120);
//		pressaoSistolicaPaciente.set("09:30",123);
//		pressaoDiastolicaPadrao.set("09:30",80);
//		pressaoDiastolicaPaciente.set("09:30",81);
//		pressaoSistolicaPadrao.set("09:40",120);
//		pressaoSistolicaPaciente.set("09:40",122);
//		pressaoDiastolicaPadrao.set("09:40",80);
//		pressaoDiastolicaPaciente.set("09:40",87);
//		pressaoSistolicaPadrao.set("09:50",120);
//		pressaoSistolicaPaciente.set("09:50",121);
//		pressaoDiastolicaPadrao.set("09:50",80);
//		pressaoDiastolicaPaciente.set("09:50",83);

		
		indicadorPressaoArterial.addSeries(pressaoSistolicaPadrao);
		indicadorPressaoArterial.addSeries(pressaoSistolicaPaciente);
		indicadorPressaoArterial.addSeries(pressaoDiastolicaPadrao);
		indicadorPressaoArterial.addSeries(pressaoDiastolicaPaciente);
		
		return indicadorPressaoArterial;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListaUsuarios() {
	listaUsuarios = (List<Usuario>) new br.ufscar.acumaaf.siaf.dao.UsuarioDAO().findUsuarioContemDadoSessao();
		return listaUsuarios;
	}	
	
	public boolean isMostrar() {
		return mostrar;
	}

	public void setMostrar(boolean mostrar) {
		this.mostrar = mostrar;
	}
}
