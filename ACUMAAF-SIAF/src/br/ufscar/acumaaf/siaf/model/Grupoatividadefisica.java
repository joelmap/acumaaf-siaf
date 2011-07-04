package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the grupoatividadefisica database table.
 * 
 */
@Entity
@Table(name="grupoatividadefisica")
public class Grupoatividadefisica implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="GRUPOATIVIDADEFISICA_CODIGOGRUPOATIVIDADEFISICA_GENERATOR", sequenceName="GRUPOATIVIDADEFISICA_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPOATIVIDADEFISICA_CODIGOGRUPOATIVIDADEFISICA_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigogrupoatividadefisica;

    @Temporal( TemporalType.DATE)
	private Date datacriacao;

    @Temporal( TemporalType.DATE)
	private Date dataencerramento;

    @Temporal( TemporalType.DATE)
	private Date datainicio;

	private Time horafim;

	private Time horainicio;

	@Column(length=300)
	private String localatividade;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(length=300)
	private String observacao;

	private Boolean sincronizado;

	@Column(nullable=false)
	private Boolean statusgrupoatividadefisica;

	//bi-directional many-to-one association to Atividadefisica
    @ManyToOne
	@JoinColumn(name="codigoatividadefisica", nullable=false)
	private Atividadefisica atividadefisica;

	//bi-directional many-to-one association to Diasemana
    @ManyToOne
	@JoinColumn(name="codigodiasemana", nullable=false)
	private Diasemana diasemana;

	//bi-directional many-to-one association to Equipesaudefamilia
    @ManyToOne
	@JoinColumn(name="codigoequipesaudefamilia", nullable=false)
	private Equipesaudefamilia equipesaudefamilia;

	//bi-directional many-to-one association to Periodicidade
    @ManyToOne
	@JoinColumn(name="codigoperiodicidade", nullable=false)
	private Periodicidade periodicidade;

	//bi-directional many-to-one association to Unidadesaude
    @ManyToOne
	@JoinColumn(name="codigounidadesaude", nullable=false)
	private Unidadesaude unidadesaude;

	//bi-directional many-to-one association to Matriculausuariogrupoatividadefisica
	@OneToMany(mappedBy="grupoatividadefisica")
	private Set<Matriculausuariogrupoatividadefisica> matriculausuariogrupoatividadefisicas;

	//bi-directional many-to-one association to Sessao
	@OneToMany(mappedBy="grupoatividadefisica")
	private Set<Sessao> sessaos;

	//bi-directional many-to-one association to Usuarioresponsavelgrupoatividadefisica
	@OneToMany(mappedBy="grupoatividadefisica")
	private Set<Usuarioresponsavelgrupoatividadefisica> usuarioresponsavelgrupoatividadefisicas;

    public Grupoatividadefisica() {
    }

	public Integer getCodigogrupoatividadefisica() {
		return this.codigogrupoatividadefisica;
	}

	public void setCodigogrupoatividadefisica(Integer codigogrupoatividadefisica) {
		this.codigogrupoatividadefisica = codigogrupoatividadefisica;
	}

	public Date getDatacriacao() {
		return this.datacriacao;
	}

	public void setDatacriacao(Date datacriacao) {
		this.datacriacao = datacriacao;
	}

	public Date getDataencerramento() {
		return this.dataencerramento;
	}

	public void setDataencerramento(Date dataencerramento) {
		this.dataencerramento = dataencerramento;
	}

	public Date getDatainicio() {
		return this.datainicio;
	}

	public void setDatainicio(Date datainicio) {
		this.datainicio = datainicio;
	}

	public Time getHorafim() {
		return this.horafim;
	}

	public void setHorafim(Time horafim) {
		this.horafim = horafim;
	}

	public Time getHorainicio() {
		return this.horainicio;
	}

	public void setHorainicio(Time horainicio) {
		this.horainicio = horainicio;
	}

	public String getLocalatividade() {
		return this.localatividade;
	}

	public void setLocalatividade(String localatividade) {
		this.localatividade = localatividade;
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

	public Boolean getStatusgrupoatividadefisica() {
		return this.statusgrupoatividadefisica;
	}

	public void setStatusgrupoatividadefisica(Boolean statusgrupoatividadefisica) {
		this.statusgrupoatividadefisica = statusgrupoatividadefisica;
	}

	public Atividadefisica getAtividadefisica() {
		return this.atividadefisica;
	}

	public void setAtividadefisica(Atividadefisica atividadefisica) {
		this.atividadefisica = atividadefisica;
	}
	
	public Diasemana getDiasemana() {
		return this.diasemana;
	}

	public void setDiasemana(Diasemana diasemana) {
		this.diasemana = diasemana;
	}
	
	public Equipesaudefamilia getEquipesaudefamilia() {
		return this.equipesaudefamilia;
	}

	public void setEquipesaudefamilia(Equipesaudefamilia equipesaudefamilia) {
		this.equipesaudefamilia = equipesaudefamilia;
	}
	
	public Periodicidade getPeriodicidade() {
		return this.periodicidade;
	}

	public void setPeriodicidade(Periodicidade periodicidade) {
		this.periodicidade = periodicidade;
	}
	
	public Unidadesaude getUnidadesaude() {
		return this.unidadesaude;
	}

	public void setUnidadesaude(Unidadesaude unidadesaude) {
		this.unidadesaude = unidadesaude;
	}
	
	public Set<Matriculausuariogrupoatividadefisica> getMatriculausuariogrupoatividadefisicas() {
		return this.matriculausuariogrupoatividadefisicas;
	}

	public void setMatriculausuariogrupoatividadefisicas(Set<Matriculausuariogrupoatividadefisica> matriculausuariogrupoatividadefisicas) {
		this.matriculausuariogrupoatividadefisicas = matriculausuariogrupoatividadefisicas;
	}
	
	public Set<Sessao> getSessaos() {
		return this.sessaos;
	}

	public void setSessaos(Set<Sessao> sessaos) {
		this.sessaos = sessaos;
	}
	
	public Set<Usuarioresponsavelgrupoatividadefisica> getUsuarioresponsavelgrupoatividadefisicas() {
		return this.usuarioresponsavelgrupoatividadefisicas;
	}

	public void setUsuarioresponsavelgrupoatividadefisicas(Set<Usuarioresponsavelgrupoatividadefisica> usuarioresponsavelgrupoatividadefisicas) {
		this.usuarioresponsavelgrupoatividadefisicas = usuarioresponsavelgrupoatividadefisicas;
	}
	
}