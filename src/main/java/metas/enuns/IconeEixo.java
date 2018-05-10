package metas.enuns;

import java.util.HashMap;

/**
 * @author Fabio Enumeração que provê identificar o icone correspondente para todos os Eixos
 *         Temáticos existentes no sistema.
 */
public enum IconeEixo {
	// DEFINIÇÃO VALORES DA ENUMERAÇÃO
	// OBS: Os valores devem ser mapeados para o nome do icone correspondenteos IDs de cada eixo temático no
	// Banco de Dados.
	/** Icone do Eixo Temático: Infraestrutura. */
	Infraestrutura(1,"infraestrutura.png"),
	/** Icone do Eixo Temático: Desenvolvimento Econômico e Social. */
	DesenvolvimentoEconomicoESocial(2,"desenvolvimento_social.png"),
	/** Icone do Eixo Temático: Segurança Pública. */
	SegurancaPublica(3,"seguranca_publica.png"),
	/** Icone do Eixo Temático: Gestão Pública. */
	GestaoPublica(4,"gestao_servicos_publicos.png"),
	/** Icone do Eixo Temático: Saúde. */
	Saude(5,"saude.png"),
	/** Icone do Eixo Temático: Esporte e Lazer. */
	EducacaoEsporteELazer(6,"esporte_lazer.png");
	
	private Integer id;
	
	private String nome;
	
	private static HashMap<Integer, IconeEixo> sm_idToEnum = new HashMap<Integer, IconeEixo>();
	
	private IconeEixo(Integer pk,String s){
		id = pk;
		nome = s;
	}
	
	public static IconeEixo idToEnum(Integer id) {
		if (sm_idToEnum.containsKey(id) == false) {
			throw new IllegalArgumentException(
					String.format("Impossível converter o id de número \"%d\" para um enumerador do tipo %s!", id,
							IconeEixo.class.getSimpleName()));
		}
		return sm_idToEnum.get(id);
	}
	
	static {
		// Armazena o id em um cache para mapear facilmente para seu valor
		// enumerado
		for (IconeEixo curEixoID : IconeEixo.values()) {
			sm_idToEnum.put(curEixoID.id, curEixoID);
		}
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public static HashMap<Integer, IconeEixo> getSm_idToEnum() {
		return sm_idToEnum;
	}
	
}
