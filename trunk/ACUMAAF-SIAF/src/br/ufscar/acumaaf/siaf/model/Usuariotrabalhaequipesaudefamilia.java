package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuariotrabalhaequipesaudefamilia database table.
 * 
 */
@Entity
@Table(name="usuariotrabalhaequipesaudefamilia")
public class Usuariotrabalhaequipesaudefamilia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIOTRABALHAEQUIPESAUDEFAMILIA_CODIGOUSUARIOTRABALHAEQUIPESAUDEFAMILIA_GENERATOR", sequenceName="USUARIOTRABALHAEQUIPESAUDEFAMILIA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIOTRABALHAEQUIPESAUDEFAMILIA_CODIGOUSUARIOTRABALHAEQUIPESAUDEFAMILIA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigousuariotrabalhaequipesaudefamilia;

	@Column(nullable=false, length=50)
	private String nomecargo;

	@Column(length=300)
	private String observacao;

	//bi-directional many-to-one association to Equipesaudefamilia
    @ManyToOne
	@JoinColumn(name="codigoequipesaudefamilia", nullable=false)
	private Equipesaudefamilia equipesaudefamilia;

	//bi-directional many-to-one association to Usuario
    @ManyToOne
	@JoinColumn(name="codigousuario", nullable=false)
	private Usuario usuario;

    public Usuariotrabalhaequipesaudefamilia() {
    }

	public Integer getCodigousuariotrabalhaequipesaudefamilia() {
		return this.codigousuariotrabalhaequipesaudefamilia;
	}

	public void setCodigousuariotrabalhaequipesaudefamilia(Integer codigousuariotrabalhaequipesaudefamilia) {
		this.codigousuariotrabalhaequipesaudefamilia = codigousuariotrabalhaequipesaudefamilia;
	}

	public String getNomecargo() {
		return this.nomecargo;
	}

	public void setNomecargo(String nomecargo) {
		this.nomecargo = nomecargo;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Equipesaudefamilia getEquipesaudefamilia() {
		return this.equipesaudefamilia;
	}

	public void setEquipesaudefamilia(Equipesaudefamilia equipesaudefamilia) {
		this.equipesaudefamilia = equipesaudefamilia;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}