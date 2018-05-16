package qualitativo.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import arquitetura.interfaces.Auditable;
import arquitetura.model.Model;


@Entity
@Table(name = "plano_interno", schema = "planejamento")
public class PlanoInterno extends Model implements Auditable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_plano_interno")
	private Long id;

	@Column(name = "cod")
	private String sigla;

	@Column(name = "titulo")
	private String titulo;

	@Column(name = "objetivo")
	private String objetivo;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_acao")
	private Acao acao=new Acao();

	@OneToMany(mappedBy = "planoInterno")
	private List<EtapasExecucao> etapasExecucaos;

	public PlanoInterno(){}
	
	public PlanoInterno(
						String unidadeGestoraCodigo,
						String unidadeGestoraSigla,
						String unidadeGestoraDescricao,
						String unidadeOrcamentariaCodigo,
						String unidadeOrcamentariaDescricao,
						String programaCodigo,
						String programaDenominacao,
						String acaoCodigo,
						String acaoDenominacao,
						String sigla,
						String titulo,
						String objetivo
						){
		
		this.sigla = sigla;
		this.titulo = titulo;
		this.objetivo = objetivo;
		
		acao=new Acao();
		
		acao.setCodigo(acaoCodigo);
		acao.setDenominacao(acaoDenominacao);
		
		acao.setPrograma(new Programa());
		acao.getPrograma().setCodigo(programaCodigo);
		acao.getPrograma().setDenominacao(programaDenominacao);
		
		acao.setUnidadeOrcamentaria(new UnidadeOrcamentaria());
		acao.getUnidadeOrcamentaria().setCodigo(unidadeOrcamentariaCodigo);
		acao.getUnidadeOrcamentaria().setDescricao(unidadeOrcamentariaDescricao);
		
		acao.getUnidadeOrcamentaria().setUnidadeGestoraCodigo(unidadeGestoraCodigo);
		acao.getUnidadeOrcamentaria().setUnidadeGestoraSigla(unidadeGestoraSigla);
		acao.getUnidadeOrcamentaria().setUnidadeGestoraDescricao(unidadeGestoraDescricao);
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
 
	

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public List<EtapasExecucao> getEtapasExecucaos() {
		return this.etapasExecucaos;
	}

	public void setEtapasExecucaos(List<EtapasExecucao> etapasExecucaos) {
		this.etapasExecucaos = etapasExecucaos;
	}

	@Override
	public String getLogDetail() {
		StringBuilder sb = new StringBuilder();
		sb.append(" Plano Interno Id : ").append(id).append(" Plano Interno Sigla: ").append(this.sigla);

		return sb.toString();
	}

}