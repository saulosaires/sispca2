package administrativo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import arquitetura.enums.TipoUsuario;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;
import qualitativo.model.UnidadeOrcamentaria;
 
 
@Entity
@Table(name = "usuario", schema = "controle_acesso")
public class Usuario  extends Model implements  Auditable {
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long id;

	@Column(name="name",length=50,nullable=false)
	private String name;

	@Column(name="login",length=30,nullable=false)
	private String login;

	@Column(name="email",length=80,nullable=false)
	private String email;

	@Column(name = "primeiro_acesso")
	private Boolean primeiroAcesso = true;

	@Column(name = "ultimo_login")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoLogin;

	@Column(name="password",length=200,nullable=false)
	private String password;

	@Column(name="tipo_usuario",length=1,nullable=false)
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario = TipoUsuario.P;
	
 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="papel",
		schema="controle_acesso",
		joinColumns=@JoinColumn(name="id_usuario"),
		inverseJoinColumns=@JoinColumn(name="id_perfil")
	)
	private List<Perfil> perfis = new ArrayList<>();

 
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_unidade_orcamentaria")
	private UnidadeOrcamentaria unidadeOrcamentaria = new UnidadeOrcamentaria();

 
	@Column(name="cpf",length=11,nullable=false)
	private String cpf;

	@Column(name="cargo")
	private String cargo;

	@Column(name="hash")
	private String hash;

	@Column(name = "validade_hash")
	private Date validadeHash;

	@Column(name= "foto" )
	private byte[] foto;

	
	@Column(name="ddd_celular",length=2)
	private String dddCelular;
	
	@Column(name="numero_celular",length=9)
	private String numeroCelular;
	
	@Column(name="ddd_residencial",length=2)
	private String dddResidencial;
	
	@Column(name="numero_residencial",length=9)
	private String numeroResidencial;
	
	private transient String senhaDescriptografada;

	private transient String loginSegundaSugestao;
	
	private transient boolean validarSameCpf;
	
	public Usuario() { 
	
	}

	public Usuario(Long id){
		this.id=id;
	}
	
	public Usuario(String name, String email, Boolean primeiroAcesso,
			Date lastLogin, String password, List<Perfil> perfis) {
		super();
		this.name = name;
		this.email = email;
		this.primeiroAcesso = primeiroAcesso;
		this.ultimoLogin = lastLogin;
		this.password = password;
		this.perfis = perfis;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPrimeiroAcesso() {
		return this.primeiroAcesso;
	}

	public void setPrimeiroAcesso(Boolean primeiroAcesso) {
		this.primeiroAcesso = primeiroAcesso;
	}

	 
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password =password;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}
 

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getValidadeHash() {
		return validadeHash;
	}

	public void setValidadeHash(Date validadeHash) {
		this.validadeHash = validadeHash;
	}
	
	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}	
	
	public String getSenhaDescriptografada() {
		return senhaDescriptografada;
	}

	public void setSenhaDescriptografada(String senhaDescriptografada) {
		this.senhaDescriptografada = senhaDescriptografada;
	}	

	public String getDddCelular() {
		return dddCelular;
	}

	public void setDddCelular(String dddCelular) {
		this.dddCelular = dddCelular;
	}

	public String getNumeroCelular() {
		return numeroCelular;
	}

	public void setNumeroCelular(String numeroCelular) {
		this.numeroCelular = numeroCelular;
	}

	public String getDddResidencial() {
		return dddResidencial;
	}

	public void setDddResidencial(String dddResidencial) {
		this.dddResidencial = dddResidencial;
	}

	public String getNumeroResidencial() {
		return numeroResidencial;
	}

	public void setNumeroResidencial(String numeroResidencial) {
		this.numeroResidencial = numeroResidencial;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	public String getLoginSegundaSugestao() {
		return loginSegundaSugestao;
	}

	public void setLoginSegundaSugestao(String loginSegundaSugestao) {
		this.loginSegundaSugestao = loginSegundaSugestao;
	}

	
	
	public boolean isValidarSameCpf() {
		return validarSameCpf;
	}

	public void setValidarSameCpf(boolean validarSameCpf) {
		this.validarSameCpf = validarSameCpf;
	}	
	
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getPerfilLabel() {
		
    	if(!perfis.isEmpty()) {
    		StringJoiner sj = new StringJoiner(",");
    	
    		for(Perfil p:perfis) {
    			sj.add(p.getName());
    		}
    		
    		return sj.toString();
    	
    	}
    	
    	return "";
		
	}
	
	@Override
	public String getLogDetail() {		
		StringBuilder sb = new StringBuilder();
		sb.append(" Usuario Id : ").append(id);
		sb.append(" Usuario name : ").append(name);
		sb.append(" Usuario login : ").append(login);
		sb.append(" Usuario cpf : ").append(cpf);
		sb.append(" Usuario email : ").append(email);
		sb.append(" Usuario primeiroAcesso : ").append(primeiroAcesso);
		sb.append(" Usuario ultimoLogin : ").append(ultimoLogin != null ? FormatoUtils.formataData(ultimoLogin) : ultimoLogin);
		sb.append(" Usuario unidadeOrcamentaria: ").append(unidadeOrcamentaria.getId());

		return sb.toString();
	}
 
 
	
}