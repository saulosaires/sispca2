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
import qualitativo.model.Programa;

public class ProgramaDAO extends AbstractDAO<Programa> {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2103883892152462274L;

	private static final String ID="id";
	private static final String CODIGO="codigo";
	private static final String DENOMINACAO="denominacao";
	private static final String ORGAO="orgao";
	private static final String OBJETIVO="objetivo";
	private static final String RESPONSAVEL="responsavel";
	private static final String EIXOS="eixos";
	private static final String INDICADOR ="indicador";
	private static final String PUBLICO_ALVO= "publicoAlvo";
	private static final String TIPO_PROGRAMA="tipoPrograma";
	private static final String TIPO_HORIZONTE_TEMPORAL="tipoHorizonteTemporal";
	private static final String EXERCICIO="exercicio";
	private static final String UNIDADE_ORCAMENTARIA="unidadeOrcamentaria";
	private static final String INDICADOR_DESEMPENHO="indicadorDesempenhoIntermediario";
	private static final String DESCRICAO="descricao";
	private static final String PROBLEMA="problema";
	
	
	public ProgramaDAO() {
		setClazz(Programa.class);

	}

	public List<Programa> findAllOrderByDenominacao() {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Programa> q = cb.createQuery(Programa.class);
		
		Root<Programa> c = q.from(Programa.class);
		
		q.where(cb.equal(c.get(ATIVO), true));
		
		q.select(c);
		q.orderBy(cb.asc(c.get(DENOMINACAO)));
 
		return  entityManager.createQuery(q).getResultList();

	}

	public List<Programa> buscar(String codigo, String denominacao,List<Long> listOrgao, Long tipoPrograma,Long exercicioId,Long eixoId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
		List<Predicate> predicate = new ArrayList<>();

		if (!Utils.emptyParam(codigo)) {

			Expression<String> upperCodigo = cb.upper(m.get(CODIGO));

			predicate.add(cb.like(upperCodigo, "%" + codigo.toUpperCase() + "%"));
		}

		if (!Utils.emptyParam(denominacao)) {

			Expression<String> upperDenominacao = cb.upper(m.get(DENOMINACAO));

			predicate.add(cb.like(upperDenominacao, "%" + denominacao.toUpperCase() + "%"));
		}
		
		if(listOrgao!=null && !listOrgao.isEmpty()) {
			
			Join<Object, Object> joinOrgao = m.join(ORGAO,JoinType.INNER);
			joinOrgao.on(cb.isTrue(joinOrgao.get(ID).in(listOrgao)) );
			 
		}
		
		if (!Utils.invalidId((tipoPrograma))) {

			Join<Object, Object> joinTipoPrograma = m.join(TIPO_PROGRAMA,JoinType.INNER);
			joinTipoPrograma.on(cb.equal(joinTipoPrograma.get(ID),tipoPrograma) );
		
		}
 
		if (!Utils.invalidId((exercicioId))) {

			Join<Object, Object> joinExercicio = m.join(EXERCICIO,JoinType.INNER);
			joinExercicio.on(cb.equal(joinExercicio.get(ID),exercicioId) );	
		
		}
		
		if (!Utils.invalidId((eixoId))) {
			Join<Object, Object> joinEixo = m.join(EIXOS,JoinType.INNER);
			joinEixo.on(cb.equal(joinEixo.get(ID),eixoId) );	
		}
		
		
		query.where(predicate.toArray(new Predicate[predicate.size()]));

		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
		 
	public List<Programa> buscarPorUnidadeOrcamentaria(String unidadeOrcamentariaCodigo) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
 
		if (!Utils.emptyParam((unidadeOrcamentariaCodigo))) {
			Join<Object, Object> joinUnidade = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
			joinUnidade.on(cb.equal(joinUnidade.get(CODIGO),unidadeOrcamentariaCodigo) );	
		}
		
		 
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
	
	public List<Programa> buscarPorUnidadeOrcamentaria(Long unidadeOrcamentariaId) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		Root<Programa> m = query.from(Programa.class);

		query.select(m);
		
 
		if (!Utils.invalidId((unidadeOrcamentariaId))) {
			Join<Object, Object> joinUnidade = m.join(UNIDADE_ORCAMENTARIA,JoinType.INNER);
			joinUnidade.on(cb.equal(joinUnidade.get(ID),unidadeOrcamentariaId) );	
		}
		
		 
		query.orderBy(cb.asc(m.get(DENOMINACAO)));
		
		
		return  entityManager.createQuery(query).getResultList();
	}
	 
	public List<Programa> exportarBI(Long exercicioId) {
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		CriteriaQuery<Programa> query = cb.createQuery(Programa.class);
		
		Root<Programa> root = query.from(Programa.class);
		Join<Object, Object> joinTipoPrograma 		 = root.join(TIPO_PROGRAMA,JoinType.LEFT);
		Join<Object, Object> joinTipoHorizonte 		 = root.join(TIPO_HORIZONTE_TEMPORAL,JoinType.LEFT);
		Join<Object, Object> joinIndicadorDesempenho = root.joinSet(INDICADOR_DESEMPENHO,JoinType.LEFT);
		Join<Object, Object> joinProgExercicio 		 = root.join(EXERCICIO, 	   JoinType.LEFT);
		
		query.multiselect(
						  root.get(CODIGO),
						  root.get(OBJETIVO),
						  root.get(RESPONSAVEL),
						  joinIndicadorDesempenho.get(INDICADOR),
						  root.get(PUBLICO_ALVO),
						  joinTipoPrograma.get(CODIGO),
						  joinTipoPrograma.get(DESCRICAO),
						  root.get(PROBLEMA),
						  joinTipoHorizonte.get(DESCRICAO)
						  );
		
		query.where(
					cb.equal(root.get(ATIVO), true),
					cb.equal(joinProgExercicio.get(ID),exercicioId)
					);
		
		return  entityManager.createQuery(query).getResultList();
	}	
	

}
