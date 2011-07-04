package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the indicador database table.
 * 
 */
@Entity
@Table(name="indicador")
public class Indicador implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="INDICADOR_CODIGOINDICADOR_GENERATOR", sequenceName="INDICADOR_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="INDICADOR_CODIGOINDICADOR_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigoindicador;

	@Column(length=300)
	private String descricao;

	@Column(nullable=false, length=50)
	private String nome;

	//bi-directional many-to-one association to Dadoindicador
	@OneToMany(mappedBy="indicador")
	private Set<Dadoindicador> dadoindicadors;

    public Indicador() {
    }

	public Integer getCodigoindicador() {
		return this.codigoindicador;
	}

	public void setCodigoindicador(Integer codigoindicador) {
		this.codigoindicador = codigoindicador;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Dadoindicador> getDadoindicadors() {
		return this.dadoindicadors;
	}

	public void setDadoindicadors(Set<Dadoindicador> dadoindicadors) {
		this.dadoindicadors = dadoindicadors;
	}
	
}