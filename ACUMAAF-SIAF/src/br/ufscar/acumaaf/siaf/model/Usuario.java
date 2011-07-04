package br.ufscar.acumaaf.siaf.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="USUARIO_CODIGOUSUARIO_GENERATOR", sequenceName="USUARIO_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="USUARIO_CODIGOUSUARIO_GENERATOR")
	@Column(unique=true, nullable=false)
	private Integer codigousuario;

    @Temporal( TemporalType.DATE)
	private Date datacadastro;

    @Temporal( TemporalType.DATE)
	private Date datamodificacao;

    @Temporal( TemporalType.DATE)
	private Date datanascimento;

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

	@Column(length=100)
	private String foto;

	@Column(nullable=false, length=50)
	private String nome;

	@Column(length=20)
	private String numerodocumento;

	@Column(nullable=false, length=30)
	private String senha;

	@Column(length=1)
	private String sexo;

	private Boolean sincronizado;

	@Column(length=15)
	private String telefone;

	private Integer tipodocumento;

	@Column(nullable=false)
	private Integer tipousuario;

	@Column(nullable=false, length=30)
	private String usuario;

	private Integer usuariomodificacao;

	//bi-directional many-to-one association to Dadoindicador
	@OneToMany(mappedBy="usuario")
	private Set<Dadoindicador> dadoindicadors;

	//bi-directional many-to-one association to Dadopadraosessao
	@OneToMany(mappedBy="usuario")
	private Set<Dadopadraosessao> dadopadraosessaos;

	//bi-directional many-to-one association to Dadosessao
	@OneToMany(mappedBy="usuario")
	private Set<Dadosessao> dadosessaos;

	//bi-directional many-to-one association to Ipaq
	@OneToMany(mappedBy="usuario")
	private Set<Ipaq> ipaqs;

	//bi-directional many-to-one association to Matriculausuariogrupoatividadefisica
	@OneToMany(mappedBy="usuario")
	private Set<Matriculausuariogrupoatividadefisica> matriculausuariogrupoatividadefisicas;

	//bi-directional many-to-one association to Msnusuario
	@OneToMany(mappedBy="usuario1")
	private Set<Msnusuario> msnusuarios1;

	//bi-directional many-to-one association to Msnusuario
	@OneToMany(mappedBy="usuario2")
	private Set<Msnusuario> msnusuarios2;

	//bi-directional many-to-one association to Presenca
	@OneToMany(mappedBy="usuario")
	private Set<Presenca> presencas;

	//bi-directional many-to-one association to Usuarioresponsavelgrupoatividadefisica
	@OneToMany(mappedBy="usuario")
	private Set<Usuarioresponsavelgrupoatividadefisica> usuarioresponsavelgrupoatividadefisicas;

	//bi-directional many-to-one association to Usuariotrabalhaequipesaudefamilia
	@OneToMany(mappedBy="usuario")
	private Set<Usuariotrabalhaequipesaudefamilia> usuariotrabalhaequipesaudefamilias;

	//bi-directional many-to-one association to Vinculousuariounidadedesaude
	@OneToMany(mappedBy="usuario")
	private Set<Vinculousuariounidadedesaude> vinculousuariounidadedesaudes;

    public Usuario() {
    }
    
   	public Integer getCodigousuario() {
		return this.codigousuario;
	}

	public void setCodigousuario(Integer codigousuario) {
		this.codigousuario = codigousuario;
	}

	public Date getDatacadastro() {
		return this.datacadastro;
	}

	public void setDatacadastro(Date datacadastro) {
		this.datacadastro = datacadastro;
	}

	public Date getDatamodificacao() {
		return this.datamodificacao;
	}

	public void setDatamodificacao(Date datamodificacao) {
		this.datamodificacao = datamodificacao;
	}

	public Date getDatanascimento() {
		return this.datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
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

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumerodocumento() {
		return this.numerodocumento;
	}

	public void setNumerodocumento(String numerodocumento) {
		this.numerodocumento = numerodocumento;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public Integer getTipodocumento() {
		return this.tipodocumento;
	}

	public void setTipodocumento(Integer tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Integer getTipousuario() {
		return this.tipousuario;
	}

	public void setTipousuario(Integer tipousuario) {
		this.tipousuario = tipousuario;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getUsuariomodificacao() {
		return this.usuariomodificacao;
	}

	public void setUsuariomodificacao(Integer usuariomodificacao) {
		this.usuariomodificacao = usuariomodificacao;
	}

	public Set<Dadoindicador> getDadoindicadors() {
		return this.dadoindicadors;
	}

	public void setDadoindicadors(Set<Dadoindicador> dadoindicadors) {
		this.dadoindicadors = dadoindicadors;
	}
	
	public Set<Dadopadraosessao> getDadopadraosessaos() {
		return this.dadopadraosessaos;
	}

	public void setDadopadraosessaos(Set<Dadopadraosessao> dadopadraosessaos) {
		this.dadopadraosessaos = dadopadraosessaos;
	}
	
	public Set<Dadosessao> getDadosessaos() {
		return this.dadosessaos;
	}

	public void setDadosessaos(Set<Dadosessao> dadosessaos) {
		this.dadosessaos = dadosessaos;
	}
	
	public Set<Ipaq> getIpaqs() {
		return this.ipaqs;
	}

	public void setIpaqs(Set<Ipaq> ipaqs) {
		this.ipaqs = ipaqs;
	}
	
	public Set<Matriculausuariogrupoatividadefisica> getMatriculausuariogrupoatividadefisicas() {
		return this.matriculausuariogrupoatividadefisicas;
	}

	public void setMatriculausuariogrupoatividadefisicas(Set<Matriculausuariogrupoatividadefisica> matriculausuariogrupoatividadefisicas) {
		this.matriculausuariogrupoatividadefisicas = matriculausuariogrupoatividadefisicas;
	}
	
	public Set<Msnusuario> getMsnusuarios1() {
		return this.msnusuarios1;
	}

	public void setMsnusuarios1(Set<Msnusuario> msnusuarios1) {
		this.msnusuarios1 = msnusuarios1;
	}
	
	public Set<Msnusuario> getMsnusuarios2() {
		return this.msnusuarios2;
	}

	public void setMsnusuarios2(Set<Msnusuario> msnusuarios2) {
		this.msnusuarios2 = msnusuarios2;
	}
	
	public Set<Presenca> getPresencas() {
		return this.presencas;
	}

	public void setPresencas(Set<Presenca> presencas) {
		this.presencas = presencas;
	}
	
	public Set<Usuarioresponsavelgrupoatividadefisica> getUsuarioresponsavelgrupoatividadefisicas() {
		return this.usuarioresponsavelgrupoatividadefisicas;
	}

	public void setUsuarioresponsavelgrupoatividadefisicas(Set<Usuarioresponsavelgrupoatividadefisica> usuarioresponsavelgrupoatividadefisicas) {
		this.usuarioresponsavelgrupoatividadefisicas = usuarioresponsavelgrupoatividadefisicas;
	}
	
	public Set<Usuariotrabalhaequipesaudefamilia> getUsuariotrabalhaequipesaudefamilias() {
		return this.usuariotrabalhaequipesaudefamilias;
	}

	public void setUsuariotrabalhaequipesaudefamilias(Set<Usuariotrabalhaequipesaudefamilia> usuariotrabalhaequipesaudefamilias) {
		this.usuariotrabalhaequipesaudefamilias = usuariotrabalhaequipesaudefamilias;
	}
	
	public Set<Vinculousuariounidadedesaude> getVinculousuariounidadedesaudes() {
		return this.vinculousuariounidadedesaudes;
	}

	public void setVinculousuariounidadedesaudes(Set<Vinculousuariounidadedesaude> vinculousuariounidadedesaudes) {
		this.vinculousuariounidadedesaudes = vinculousuariounidadedesaudes;
	}
	
}