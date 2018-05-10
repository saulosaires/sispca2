package metas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import administrativo.model.Usuario;
import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;
import arquitetura.utils.FormatoUtils;
import metas.enuns.TipoUnidade;
import qualitativo.model.UnidadeOrcamentaria;

@Entity
@Table(name = "atividade", schema = "metassintetico")
public class Atividade extends Model implements Serializable,Auditable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
		
	@Column(name="comentario")
	private String comentario;

	@Column(name="nome")
	private String nome;

	@Column(name="previsto")
	private Integer previsto;

	@Column(name="realizado")
	private Integer realizado;

	@Enumerated(EnumType.STRING)
	private TipoUnidade unidade; 
	
	@ManyToOne
	@JoinColumn(name="unidade_orcamentaria_id")
	private UnidadeOrcamentaria unidadeOrcamentaria;

	 
	@ManyToOne
	private Status status;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ultima_alteracao")
	private Date ultimaAlteracao;
	
	@OneToOne
	@JoinColumn(name = "id_usuario_alterador")
	private Usuario usuarioAlterador;
	
	@ManyToMany( fetch = FetchType.LAZY )
	@JoinTable(
		name="atividade_compromisso",
		schema="metassintetico",
		joinColumns=@JoinColumn(name="atividade_id"),
		inverseJoinColumns=@JoinColumn(name="compromisso_id")
	)
	private List<Compromisso> compromissos = new ArrayList<>();
	
	@Transient
	private String codigosCompromissos;
 	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigosCompromissos() {
		return this.codigosCompromissos;
	}
	
	public void setCodigosCompromissos(String codigosCompromissos) {
		this.codigosCompromissos = codigosCompromissos;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPrevisto() {
		return this.previsto;
	}

	public void setPrevisto(Integer previsto) {
		this.previsto = previsto;
	}

	public Integer getRealizado() {
		return this.realizado;
	}

	public void setRealizado(Integer realizado) {
		this.realizado = realizado;
	}
	
	public TipoUnidade getUnidade() {
		return unidade;
	}

	public void setUnidade(TipoUnidade unidade) {
		this.unidade = unidade;
	}

 

	public UnidadeOrcamentaria getUnidadeOrcamentaria() {
		return unidadeOrcamentaria;
	}

	public void setUnidadeOrcamentaria(UnidadeOrcamentaria unidadeOrcamentaria) {
		this.unidadeOrcamentaria = unidadeOrcamentaria;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;
	}

	public Usuario getUsuarioAlterador() {
		return usuarioAlterador;
	}

	public void setUsuarioAlterador(Usuario usuarioAlterador) {
		this.usuarioAlterador = usuarioAlterador;
	}

	public List<Compromisso> getCompromissos() {
		return compromissos;
	}

	public void setCompromissos(List<Compromisso> compromissos) {
		this.compromissos = compromissos;
	}
	
	 

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Atividade Id: ").append(id);
		sb.append(" Atividade Nome: ").append(nome);
		sb.append(" Atividade Previsto: ").append(previsto);
		sb.append(" Atividade Realizado: ").append(realizado);
		sb.append(" Atividade Unidade: ").append(unidade);
		sb.append(" Atividade Comentário: ").append(comentario);
		sb.append(" Atividade Unidade Orcamentaria: ").append(unidadeOrcamentaria != null ? unidadeOrcamentaria.getId() : null);
		sb.append(" Atividade Status: ").append(status != null ? status.getNome() : null);
		sb.append(" Atividade Ultima Alteração: ").append(ultimaAlteracao != null ? FormatoUtils.formataData(ultimaAlteracao) : ultimaAlteracao );
		sb.append(" Atividade Ativo: ").append(getAtivo());

		return sb.toString();
	}
	
	public void codigosCompromissoStr() {
		String str = "";
		
		for (Compromisso compromisso : this.getCompromissos())
			str += compromisso.getCodigo() + ", ";
		
		str = str.substring(0, str.length() - 2);
		
		this.codigosCompromissos = str;
	}

}