package metas.beans;

import java.io.Serializable;
import java.util.StringJoiner;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import arquitetura.utils.Utils;
import metas.model.Atividade;
import metas.model.Compromisso;
import metas.service.AtividadeService;
import metas.service.CompromissoService;

@Named
@ViewScoped
public class MetasViewMBean implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
 
	
	private Atividade atividade;
	 
	
	private AtividadeService atividadeService;
	private CompromissoService compromissoService;
	
	@Inject
	public MetasViewMBean(AtividadeService atividadeService,CompromissoService compromissoService) {
		this.atividadeService=atividadeService;
		this.compromissoService=compromissoService;
 
	}
	
	public void init() {

		if (!Utils.invalidId(id)) {

			atividade = atividadeService.findById(id);

			if(atividade!=null && !Utils.invalidId(atividade.getId())) {
				
			   atividade.setCompromissos(compromissoService.findByAtividade(atividade.getId()));
			   
			   StringJoiner str = new StringJoiner(",");
			   for(Compromisso c : atividade.getCompromissos()) {
				   str.add(c.getCodigo());
			   }
			   atividade.setCodigosCompromissos(str.toString());
			  
			}
			
			
		}

	}
 
	public Atividade getAtividade() {
		return atividade;
	}

	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
