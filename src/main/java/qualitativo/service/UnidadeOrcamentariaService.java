package qualitativo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import administrativo.model.Usuario;
import administrativo.service.UserService;
import arquitetura.enums.TipoUsuario;
import arquitetura.service.AbstractService;
import arquitetura.utils.Utils;
import qualitativo.controller.UnidadeOrcamentariaController;
import qualitativo.model.UnidadeOrcamentaria;

public class UnidadeOrcamentariaService extends AbstractService<UnidadeOrcamentaria>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3288174422611664897L;

	private UserService userService;
	
	@Inject
	public UnidadeOrcamentariaService(UnidadeOrcamentariaController controller,UserService userService) {
		super(controller);
		
		this.userService  = userService;
	}

	 
	
	public List<UnidadeOrcamentaria> findAllOrderByDescricao(Long usuarioId) {

		Usuario user = userService.findById(usuarioId);
		
		TipoUsuario tipo = user.getTipoUsuario();
		
		List<UnidadeOrcamentaria> list=new ArrayList<>();
		
		switch(tipo) {
		
			case P:
				list.add(findById(user.getUnidadeOrcamentaria().getId()));
			break;

			case G:{
				 UnidadeOrcamentaria uo = findById(user.getUnidadeOrcamentaria().getId());

 				 list.addAll( buscar(null,null,null,uo.getOrgao().getId()));
				
			}break;
		
			case A:
			  list = findAll();
			break;
 
		}
		
		return orderByDescricao(list);
		
		
		
	}

	public List<UnidadeOrcamentaria> buscar(Long usuarioId,String codigo, String descricao, Long orgaoId) {


		if(Utils.emptyParam(codigo) && Utils.emptyParam(descricao) && Utils.invalidId(orgaoId)) {
			return findAllOrderByDescricao(usuarioId);
		}else {
			return ((UnidadeOrcamentariaController) getController()).buscar(codigo, descricao, orgaoId);
		}
	}
	
	
	private List<UnidadeOrcamentaria> orderByDescricao(List<UnidadeOrcamentaria> list){
		
		return list.stream().sorted((o1, o2)->o1.getDescricao().compareTo(o2.getDescricao())).
          														collect(Collectors.toList());
		
	}

}
