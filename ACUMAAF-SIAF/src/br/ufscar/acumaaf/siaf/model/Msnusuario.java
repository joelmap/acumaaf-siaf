package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the msnusuario database table.
 * 
 */
@Entity
@Table(name="msnusuario")
public class Msnusuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="MSNUSUARIO_CODIGOMSNUSUARIO_GENERATOR", sequenceName="MSNUSUARIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MSNUSUARIO_CODIGOMSNUSUARIO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigomsnusuario;

    @Temporal( TemporalType.DATE)
	@Column(nullable=false)
	private Date data;

	@Column(nullable=false, length=500)
	private String mensagem;

	@Column(nullable=false)
	private Boolean statusmensagem;

	@Column(nullable=false, length=30)
	private String titulo;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuarioorigem", nullable=false)
	private Usuario usuario1;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuariodestino", nullable=false)
	private Usuario usuario2;

    public Msnusuario() {
    }

	public Integer getCodigomsnusuario() {
		return this.codigomsnusuario;
	}

	public void setCodigomsnusuario(Integer codigomsnusuario) {
		this.codigomsnusuario = codigomsnusuario;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getMensagem() {
		return this.mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Boolean getStatusmensagem() {
		return this.statusmensagem;
	}

	public void setStatusmensagem(Boolean statusmensagem) {
		this.statusmensagem = statusmensagem;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(Usuario usuario1) {
		this.usuario1 = usuario1;
	}
	
	public Usuario getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(Usuario usuario2) {
		this.usuario2 = usuario2;
	}
	
}