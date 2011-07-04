package br.ufscar.acumaaf.siaf.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.ufscar.acumaaf.siaf.dao.IpaqDAO;
import br.ufscar.acumaaf.siaf.model.Ipaq;
import br.ufscar.acumaaf.siaf.model.Usuario;


@ManagedBean(name="ipaqMB")
@ViewScoped
public class IpaqManagedBean {

	private Ipaq ipaq;
	private IpaqDAO ipaqDAO = new IpaqDAO();
	private List<Ipaq> listaIpaq;
	private Ipaq ipaqSelecionado = new Ipaq();
	private boolean ativo = false;
	private Usuario usuario = new Usuario();
	private List<Usuario> listaUsuarios;
	SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
	Date data;
	
	// Construtor da Classe ManagedBean
	public IpaqManagedBean() {
		listaIpaq = new ArrayList<Ipaq>();
		data = new Date();
		try {
			data = (java.util.Date) formatoData.parse(formatoData.format(data));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		ipaq = new Ipaq(null, 0, 0, 0, 0, 0, 0, 0, 0, 0, data, "", 0, 0, 0, 0, usuario);
	}

	public Ipaq getIpaq() {
		return ipaq;
	}

	public void setIpaq(Ipaq ipaq) {
		this.ipaq = ipaq;
	}

	public Ipaq getIpaqSelecionado() {
		return ipaqSelecionado;
	}

	public void setIpaqSelecionado(Ipaq ipaqSelecionado) {
		this.ipaqSelecionado = ipaqSelecionado;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Ipaq> getListaIpaq() {
		listaIpaq = ipaqDAO.findIpaqAll();
		return listaIpaq;
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

	public String cadastrarIpaq(){
		
		//Seta o Participante que foi escolhido pelo usuário 
		ipaq.setUsuario(usuario);
			
		System.out.println("Código....................:" + ipaq.getCodigoipaq());
		System.out.println("Código Usuário............:" + ipaq.getUsuario().getCodigousuario());
		System.out.println("Data......................:" + ipaq.getData());
		System.out.println("Atividade Moderada Dias...:" + ipaq.getAtividademoderadadias());
		System.out.println("Atividade Moderada Horas..:" + ipaq.getAtividademoderadahoras());
		System.out.println("Atividade Moderada Minutos:" + ipaq.getAtividademoderadaminutos());
		System.out.println("Atividade Vigorosa Dias...:" + ipaq.getAtividadevigorosadias());
		System.out.println("Atividade Vigorosa Horas..:" + ipaq.getAtividadevigorosahoras());
		System.out.println("Atividade Vigorosa Minutos:" + ipaq.getAtividadevigorosaminutos());
		System.out.println("Caminhada Dias............:" + ipaq.getCaminhadadias());
		System.out.println("Caminhada Horas...........:" + ipaq.getCaminhadahoras());
		System.out.println("Caminhada Minutos.........:" + ipaq.getCaminhadaminutos());
		System.out.println("Tempo Sentado Dia Horas...:" + ipaq.getTemposentadodiahoras());
		System.out.println("Tempo Sentado Dia Minutos.:" + ipaq.getTemposentadodiaminutos());
		System.out.println("Tempo Sentado FS Horas....:" + ipaq.getTemposentadofimsemanahoras());
		System.out.println("Tempo Sentado FS Minutos..:" + ipaq.getTemposentadofimsemanaminutos());
		System.out.println("Observação................:" + ipaq.getObservacao());
		
		ipaqDAO.save(ipaq);
		
		return "/ipaq/listarIpaqs.xhtml?faces-redirect=true";
	}
	
	//Método chamado para editar informações de um registro selecionado pelo usuário
	public void editar(){
		
		ipaqDAO.update(ipaqSelecionado);
    }
	
	//Método chamado para deletar um registro selecionado pelo usuário
	public void deletar(){
		
		ipaqDAO.delete(ipaqSelecionado);
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página de cadastrar IPAQ
	public String chamaPaginaCadastrarIpaq(){
		
		return "/ipaq/cadastrarIpaq.xhtml?faces-redirect=true";
	}
	
	//Método chamado para navegação implícita, levando o usuário para a página que listar todos os registros de IPAQ cadastrados
	public String chamaPaginaListarIpaqs(){
		
		return "/ipaq/listarIpaqs.xhtml?faces-redirect=true";
	}
	
}
