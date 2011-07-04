package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the unidadesaude database table.
 * 
 */
@Entity
@Table(name="unidadesaude")
public class Unidadesaude implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="UNIDADESAUDE_CODIGOUNIDADESAUDE_GENERATOR", sequenceName="UNIDADESAUDE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDADESAUDE_CODIGOUNIDADESAUDE_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigounidadesaude;

	@Column(length=30)
	private String email;

	@Column(length=50)
	private String enderecobairro;

	@Column(length=15)
	private String enderecocep;

	@Column(length=50)
	private String enderecocidade;

	@Column(length=50)
	private String enderecorua;

	@Column(length=2)
	private String enderecouf;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(length=300)
	private String observacao;

	private Boolean sincronizado;

	@Column(length=15)
	private String telefone;

	//bi-directional many-to-one association to Equipesaudefamilia
	@OneToMany(mappedBy="unidadesaude")
	private Set<Equipesaudefamilia> equipesaudefamilias;

	//bi-directional many-to-one association to Grupoatividadefisica
	@OneToMany(mappedBy="unidadesaude")
	private Set<Grupoatividadefisica> grupoatividadefisicas;

	//bi-directional many-to-one association to Vinculousuariounidadedesaude
	@OneToMany(mappedBy="unidadesaude")
	private Set<Vinculousuariounidadedesaude> vinculousuariounidadedesaudes;

    public Unidadesaude() {
    }

	public Integer getCodigounidadesaude() {
		return this.codigounidadesaude;
	}

	public void setCodigounidadesaude(Integer codigounidadesaude) {
		this.codigounidadesaude = codigounidadesaude;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnderecobairro() {
		return this.enderecobairro;
	}

	public void setEnderecobairro(String enderecobairro) {
		this.enderecobairro = enderecobairro;
	}

	public String getEnderecocep() {
		return this.enderecocep;
	}

	public void setEnderecocep(String enderecocep) {
		this.enderecocep = enderecocep;
	}

	public String getEnderecocidade() {
		return this.enderecocidade;
	}

	public void setEnderecocidade(String enderecocidade) {
		this.enderecocidade = enderecocidade;
	}

	public String getEnderecorua() {
		return this.enderecorua;
	}

	public void setEnderecorua(String enderecorua) {
		this.enderecorua = enderecorua;
	}

	public String getEnderecouf() {
		return this.enderecouf;
	}

	public void setEnderecouf(String enderecouf) {
		this.enderecouf = enderecouf;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Boolean getSincronizado() {
		return this.sincronizado;
	}

	public void setSincronizado(Boolean sincronizado) {
		this.sincronizado = sincronizado;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Set<Equipesaudefamilia> getEquipesaudefamilias() {
		return this.equipesaudefamilias;
	}

	public void setEquipesaudefamilias(Set<Equipesaudefamilia> equipesaudefamilias) {
		this.equipesaudefamilias = equipesaudefamilias;
	}
	
	public Set<Grupoatividadefisica> getGrupoatividadefisicas() {
		return this.grupoatividadefisicas;
	}

	public void setGrupoatividadefisicas(Set<Grupoatividadefisica> grupoatividadefisicas) {
		this.grupoatividadefisicas = grupoatividadefisicas;
	}
	
	public Set<Vinculousuariounidadedesaude> getVinculousuariounidadedesaudes() {
		return this.vinculousuariounidadedesaudes;
	}

	public void setVinculousuariounidadedesaudes(Set<Vinculousuariounidadedesaude> vinculousuariounidadedesaudes) {
		this.vinculousuariounidadedesaudes = vinculousuariounidadedesaudes;
	}
	
}