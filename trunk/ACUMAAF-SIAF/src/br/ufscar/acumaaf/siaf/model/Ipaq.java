package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ipaq database table.
 * 
 */
@Entity
@Table(name="ipaq")
public class Ipaq implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="IPAQ_CODIGOIPAQ_GENERATOR", sequenceName="IPAQ_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IPAQ_CODIGOIPAQ_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoipaq;

	private Integer atividademoderadadias;

	private Integer atividademoderadahoras;

	private Integer atividademoderadaminutos;

	private Integer atividadevigorosadias;

	private Integer atividadevigorosahoras;

	private Integer atividadevigorosaminutos;

	private Integer caminhadadias;

	private Integer caminhadahoras;

	private Integer caminhadaminutos;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Column(length=300)
	private String observacao;

	private Integer temposentadodiahoras;

	private Integer temposentadodiaminutos;

	private Integer temposentadofimsemanahoras;

	private Integer temposentadofimsemanaminutos;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Ipaq() {
    }
    
    public Ipaq(Integer codigoipaq, Integer atividademoderadadias,
			Integer atividademoderadahoras, Integer atividademoderadaminutos,
			Integer atividadevigorosadias, Integer atividadevigorosahoras,
			Integer atividadevigorosaminutos, Integer caminhadadias,
			Integer caminhadahoras, Integer caminhadaminutos, Date data,
			String observacao, Integer temposentadodiahoras,
			Integer temposentadodiaminutos, Integer temposentadofimsemanahoras,
			Integer temposentadofimsemanaminutos, Usuario usuario) {
		this.codigoipaq = codigoipaq;
		this.atividademoderadadias = atividademoderadadias;
		this.atividademoderadahoras = atividademoderadahoras;
		this.atividademoderadaminutos = atividademoderadaminutos;
		this.atividadevigorosadias = atividadevigorosadias;
		this.atividadevigorosahoras = atividadevigorosahoras;
		this.atividadevigorosaminutos = atividadevigorosaminutos;
		this.caminhadadias = caminhadadias;
		this.caminhadahoras = caminhadahoras;
		this.caminhadaminutos = caminhadaminutos;
		this.data = data;
		this.observacao = observacao;
		this.temposentadodiahoras = temposentadodiahoras;
		this.temposentadodiaminutos = temposentadodiaminutos;
		this.temposentadofimsemanahoras = temposentadofimsemanahoras;
		this.temposentadofimsemanaminutos = temposentadofimsemanaminutos;
		this.usuario = usuario;
	}

	public Integer getCodigoipaq() {
		return this.codigoipaq;
	}

	public void setCodigoipaq(Integer codigoipaq) {
		this.codigoipaq = codigoipaq;
	}

	public Integer getAtividademoderadadias() {
		return this.atividademoderadadias;
	}

	public void setAtividademoderadadias(Integer atividademoderadadias) {
		this.atividademoderadadias = atividademoderadadias;
	}

	public Integer getAtividademoderadahoras() {
		return this.atividademoderadahoras;
	}

	public void setAtividademoderadahoras(Integer atividademoderadahoras) {
		this.atividademoderadahoras = atividademoderadahoras;
	}

	public Integer getAtividademoderadaminutos() {
		return this.atividademoderadaminutos;
	}

	public void setAtividademoderadaminutos(Integer atividademoderadaminutos) {
		this.atividademoderadaminutos = atividademoderadaminutos;
	}

	public Integer getAtividadevigorosadias() {
		return this.atividadevigorosadias;
	}

	public void setAtividadevigorosadias(Integer atividadevigorosadias) {
		this.atividadevigorosadias = atividadevigorosadias;
	}

	public Integer getAtividadevigorosahoras() {
		return this.atividadevigorosahoras;
	}

	public void setAtividadevigorosahoras(Integer atividadevigorosahoras) {
		this.atividadevigorosahoras = atividadevigorosahoras;
	}

	public Integer getAtividadevigorosaminutos() {
		return this.atividadevigorosaminutos;
	}

	public void setAtividadevigorosaminutos(Integer atividadevigorosaminutos) {
		this.atividadevigorosaminutos = atividadevigorosaminutos;
	}

	public Integer getCaminhadadias() {
		return this.caminhadadias;
	}

	public void setCaminhadadias(Integer caminhadadias) {
		this.caminhadadias = caminhadadias;
	}

	public Integer getCaminhadahoras() {
		return this.caminhadahoras;
	}

	public void setCaminhadahoras(Integer caminhadahoras) {
		this.caminhadahoras = caminhadahoras;
	}

	public Integer getCaminhadaminutos() {
		return this.caminhadaminutos;
	}

	public void setCaminhadaminutos(Integer caminhadaminutos) {
		this.caminhadaminutos = caminhadaminutos;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getTemposentadodiahoras() {
		return this.temposentadodiahoras;
	}

	public void setTemposentadodiahoras(Integer temposentadodiahoras) {
		this.temposentadodiahoras = temposentadodiahoras;
	}

	public Integer getTemposentadodiaminutos() {
		return this.temposentadodiaminutos;
	}

	public void setTemposentadodiaminutos(Integer temposentadodiaminutos) {
		this.temposentadodiaminutos = temposentadodiaminutos;
	}

	public Integer getTemposentadofimsemanahoras() {
		return this.temposentadofimsemanahoras;
	}

	public void setTemposentadofimsemanahoras(Integer temposentadofimsemanahoras) {
		this.temposentadofimsemanahoras = temposentadofimsemanahoras;
	}

	public Integer getTemposentadofimsemanaminutos() {
		return this.temposentadofimsemanaminutos;
	}

	public void setTemposentadofimsemanaminutos(Integer temposentadofimsemanaminutos) {
		this.temposentadofimsemanaminutos = temposentadofimsemanaminutos;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}