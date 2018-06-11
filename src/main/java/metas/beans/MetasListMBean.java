package metas.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import administrativo.model.Usuario;
import arquitetura.utils.SessionUtils;
import arquitetura.utils.Utils;
import metas.model.Atividade;
import metas.service.AtividadeService;
import qualitativo.model.UnidadeOrcamentaria;

@Named
@ViewScoped
public class MetasListMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String nome;
	private Long unidadeOrcamentaria;
	
	private List<UnidadeOrcamentaria>listUnidadeOrcamentaria;
	private List<Atividade> listAtividade;
	
	private AtividadeService atividadeService;
	
	@Inject
	public MetasListMBean(AtividadeService atividadeService) {
		this.atividadeService=atividadeService;
	
		Usuario user = (Usuario) SessionUtils.get(SessionUtils.USER);
		
		if(user!=null && user.getUnidadeOrcamentaria()!=null && !Utils.invalidId(user.getUnidadeOrcamentaria().getId())) {
			unidadeOrcamentaria = user.getUnidadeOrcamentaria().getId();
			 
		}

	
	}
	
	public void buscar() {
		
		listAtividade = atividadeService.findByNomeAndUnidadeOrcamentaria(nome, unidadeOrcamentaria);
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

 

	public List<UnidadeOrcamentaria> getListUnidadeOrcamentaria() {
		return listUnidadeOrcamentaria;
	}

	public void setListUnidadeOrcamentaria(List<UnidadeOrcamentaria> listUnidadeOrcamentaria) {
		this.listUnidadeOrcamentaria = listUnidadeOrcamentaria;
	}

	public List<Atividade> getListAtividade() {
		return listAtividade;
	}

	public void setListAtividade(List<Atividade> listAtividade) {
		this.listAtividade = listAtividade;
	}
	
	
	
}
