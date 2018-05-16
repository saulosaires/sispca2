package qualitativo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import arquitetura.dao.AbstractDAO;
import arquitetura.utils.Utils;
import qualitativo.model.PlanoInterno;

public class PlanoInternoDAO extends AbstractDAO<PlanoInterno> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final  String ACAO="acao"; 
	private static final  String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria"; 
	private static final  String UNIDADES_GESTORAS="unidadeGestoras";
	private static final  String EXERCICIO="exercicio";
	private static final  String PROGRAMA="programa";
	
	private static final  String SIGLA="sigla";
	private static final  String TITULO="titulo";
	private static final  String OBJETIVO="objetivo";
	private static final  String CODIGO="codigo";
	private static final  String DESCRICAO="descricao";
	private static final  String DENOMINACAO="denominacao";
	private static final  String ATIVO="ativo";
	private static final  String ID="id";
	
	public PlanoInternoDAO() {
		setClazz(PlanoInterno.class);

	}
	
	public List<PlanoInterno> relatorio(Long unidadeGestoraId,Long unidadeOrcamentariaId,Long acaoId,Long exercicioId){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoInterno> query = cb.createQuery(PlanoInterno.class);
		Root<PlanoInterno> root = query.from(PlanoInterno.class);

		Join<Object, Object> joinAcao 			 	 = root.join(ACAO, 			   	       		          JoinType.LEFT);	
		Join<Object, Object> joinUnidadeOrcamentaria = joinAcao.join(UNIDADE_ORCAMENTARIA, 			      JoinType.LEFT);
		Join<Object, Object> joinPrograma 			 = joinAcao.join(PROGRAMA, 			   		          JoinType.LEFT);	
		Join<Object, Object> joinUnidadeGestora		 = joinUnidadeOrcamentaria.joinSet(UNIDADES_GESTORAS, JoinType.LEFT);	
		Join<Object, Object> joinAcaoExercicio 		 = joinAcao.join(EXERCICIO, 			   		      JoinType.LEFT);
	 
		query.multiselect(
							joinUnidadeGestora.get(CODIGO),
							joinUnidadeGestora.get(SIGLA),
							joinUnidadeGestora.get(DESCRICAO),
							
							joinUnidadeOrcamentaria.get(CODIGO),
							joinUnidadeOrcamentaria.get(DESCRICAO),
							
							joinPrograma.get(CODIGO),
							joinPrograma.get(DENOMINACAO),			 
						  	
							joinAcao.get(CODIGO),
							joinAcao.get(DENOMINACAO),			 
							 
							root.get(SIGLA),
							root.get(TITULO),	
							root.get(OBJETIVO)
				 
						  );

		List<Predicate> predicate = new ArrayList<>();
		
		predicate.add(cb.equal(root.get(ATIVO),					  true));
		predicate.add(cb.equal(joinPrograma.get(ATIVO),			  true));
		predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ATIVO),true));
		predicate.add(cb.equal(joinAcao.get(ATIVO),			      true));
		
		if(!Utils.invalidId(unidadeGestoraId)) {
			predicate.add(cb.equal(joinUnidadeGestora.get(ID),unidadeGestoraId));
		}
		
		if(!Utils.invalidId(unidadeOrcamentariaId)) {
			predicate.add(cb.equal(joinUnidadeOrcamentaria.get(ID),unidadeOrcamentariaId));
		}

		if(!Utils.invalidId(acaoId)) {
			predicate.add(cb.equal(joinAcao.get(ID),acaoId));
		}

		if(!Utils.invalidId(exercicioId)) {
			predicate.add(cb.equal(joinAcaoExercicio.get(ID),exercicioId)); 
		}

		query.where( predicate.toArray(new Predicate[predicate.size()]));
		
		query.orderBy(
			          cb.asc(joinUnidadeGestora.get(CODIGO)),
			          cb.asc(joinUnidadeOrcamentaria.get(CODIGO)),
			          cb.asc(joinPrograma.get(CODIGO)),
			          cb.asc(joinAcao.get(CODIGO)),
			          cb.asc(root.get(SIGLA))
			        );

		return entityManager.createQuery(query).getResultList();
		
	}
	

	public List<PlanoInterno> buscar(String codigo) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlanoInterno> query = cb.createQuery(PlanoInterno.class);
		Root<PlanoInterno> m = query.from(PlanoInterno.class);

		query.select(m);

		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get(SIGLA));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(TITULO)));

		return entityManager.createQuery(query).getResultList();
	}

}
