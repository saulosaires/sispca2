package siafem.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import administrativo.model.Exercicio;
import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.Acao;
import qualitativo.model.Programa;
import qualitativo.model.TipoCalculoMeta;
import qualitativo.model.UnidadeMedida;
import qualitativo.model.UnidadeOrcamentaria;
import siafem.model.FisicoFinanceiroMensalSiafem;

public class FisicoFinanceiroMensalSiafemDAO extends AbstractDAO<FisicoFinanceiroMensalSiafem> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String ID="id"; 
	private static final  String ANO="ano";
	private static final  String QUANTIDADE="quantidade";
	private static final  String DENOMINACAO="denominacao";
	private static final  String ACAO="acao"; 
	private static final  String DESCRICAO="descricao"; 
	private static final  String PRODUTO="produto"; 
	private static final  String PROGRAMA="programa"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String UNIDADE_MEDIDA="unidadeMedida"; 
	private static final  String TIPO_CALCULO_META="tipoCalculoMeta"; 
	private static final  String DOTACAO_INICIAL="dotacaoInicial"; 
	private static final  String DISPONIVEL="disponivel";
	private static final  String EMPENHADO="empenhado";
	private static final  String LIQUIDADO="liquidado";
	
	public FisicoFinanceiroMensalSiafemDAO() {
		setClazz(FisicoFinanceiroMensalSiafem.class);

	}
  
	public List<FisicoFinanceiroMensalSiafem> analiseFisicoFinanceiro(Programa programa, Exercicio exercicio){
		
		if(programa==null  || Utils.invalidId(programa.getId()) || 
		   exercicio==null || Utils.invalidId(exercicio.getId()))return new ArrayList<>();
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<FisicoFinanceiroMensalSiafem> criteria = builder.createQuery(FisicoFinanceiroMensalSiafem.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Root<Acao> rootAcao 				  			  		= criteria.from(Acao.class);
		Root<UnidadeMedida> rootUnidadeMedida 			  		= criteria.from(UnidadeMedida.class);
		Root<Programa> rootPrograma			 			 	    = criteria.from(Programa.class);
		Root<UnidadeOrcamentaria> rootUnidadeOrcamentaria 		= criteria.from(UnidadeOrcamentaria.class);
		Root<TipoCalculoMeta> rootTipoCalculoMeta 		  	    = criteria.from(TipoCalculoMeta.class);
  		
		
	 	criteria.multiselect(
							  root.get(PROGRAMA),
							  root.get(UNIDADE_ORCAMENTARIA),
							  rootUnidadeOrcamentaria.get(DESCRICAO),
							  root.get(ACAO),
							  rootAcao.get(ID),
							  rootAcao.get(DENOMINACAO),
							  rootAcao.get(PRODUTO),
							  rootTipoCalculoMeta.get(ID), 
							  builder.sum(root.get(DOTACAO_INICIAL)),
							  builder.sum(root.get(DISPONIVEL)),
							  builder.sum(root.get(EMPENHADO)),
							  builder.sum(root.get(LIQUIDADO))
							 );

 
		criteria.where(
						builder.equal(root.get(ACAO),				   rootAcao.get(ID)),
						builder.equal(rootUnidadeMedida.get(ID),	   rootAcao.get(UNIDADE_MEDIDA).get(ID)),
						builder.equal(rootPrograma.get(ID),	  		   rootAcao.get(PROGRAMA).get(ID)),
						builder.equal(rootUnidadeOrcamentaria.get(ID), rootAcao.get(UNIDADE_ORCAMENTARIA).get(ID)),
						builder.equal(rootTipoCalculoMeta.get(ID),	   rootAcao.get(TIPO_CALCULO_META).get(ID)),
						 
						builder.equal(root.get(PROGRAMA),			   programa.getId()),
						builder.equal(root.get(ANO),				   exercicio.getAno())
				   );
		
		criteria.groupBy(
						  root.get(PROGRAMA),
						  root.get(UNIDADE_ORCAMENTARIA),
						  rootUnidadeOrcamentaria.get(DESCRICAO),
						  root.get(ACAO),
						  rootAcao.get(ID),
						  rootAcao.get(DENOMINACAO),
						  rootAcao.get(PRODUTO),
						  rootTipoCalculoMeta.get(ID)
						  );
 		
		 return entityManager.createQuery(criteria).getResultList();
	}
 
	
	public BigDecimal calculaDotacaoInicialPorUoProg(Long programaId, Integer anoVigente){
 
		
		if(Utils.invalidId(programaId) || anoVigente==null)return new BigDecimal(0);
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> dotacaoInicial = root.get(DOTACAO_INICIAL);
		Expression<BigDecimal> soma = builder.sum(dotacaoInicial);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaId.toString() ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
		
		
	 return query.getSingleResult();
		
		
	}
	 
	public BigDecimal calculaDotacaoAtualPorUoProg(Long programaId, Integer anoVigente){
 
		
		if(Utils.invalidId(programaId) || anoVigente==null)return null;
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<BigDecimal> criteria = builder.createQuery(BigDecimal.class);
		
		Root<FisicoFinanceiroMensalSiafem> root = criteria.from(FisicoFinanceiroMensalSiafem.class);
		
		Path<BigDecimal> disponivel = root.get(DISPONIVEL);
		Expression<BigDecimal> soma = builder.sum(disponivel);
		criteria.select(soma);
		
		criteria.where(
					 builder.equal(root.get(PROGRAMA),programaId.toString() ),
					 builder.equal(root.get(ANO),anoVigente )
			    );
		
		TypedQuery<BigDecimal> query = entityManager.createQuery(criteria);
 
		
	 return query.getSingleResult();
		
		
	}
 

}
