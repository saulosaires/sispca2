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
import qualitativo.controller.OrgaoController;
import qualitativo.model.Orgao;

public class OrgaoService extends AbstractService<Orgao>  {
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private UserService userService;
	
	@Inject
	public OrgaoService(OrgaoController controller,UserService userService) {
		super(controller);
	}
 
	
	public List<Orgao> buscar(Long usuarioId,String codigo,String sigla,String descricao) {

		if(Utils.emptyParam(codigo) && Utils.emptyParam(sigla) && Utils.emptyParam(descricao)) {
			return findAllOrderByDescricao(usuarioId);
		}else {
			return ((OrgaoController)getController()).buscar(codigo,sigla,descricao);
		}
		
		 
	}

	public List<Orgao> findAllOrderByDescricao(Long usuarioId) {
		 
		Usuario user = userService.findById(usuarioId);
		
		TipoUsuario tipo = user.getTipoUsuario();

		List<Orgao> list = new ArrayList<>();
		
		switch(tipo) {
		
			case P:
			case G:
				list.add(findById(user.getUnidadeOrcamentaria().getOrgao().getId()));
			break;
	 
			case A:
			  list = findAll();
			break;

		}
	
	return orderByDescricao(list);
	
 
	}	
	
	private List<Orgao> orderByDescricao(List<Orgao> list){
		
		return list.stream().sorted((o1, o2)->o1.getDescricao().compareTo(o2.getDescricao())).
          														collect(Collectors.toList());
		
	}

	

}
