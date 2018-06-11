package arquitetura.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import qualitativo.model.Orgao;

public class OrgaoUtils {

	private OrgaoUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Long> parseOrgao(Long orgao, List<Orgao> listOrgao){
		
		List<Long> list =new ArrayList<>();
		
		if(orgao!=null) {
			list.add(orgao);
		}else if(listOrgao!=null && !listOrgao.isEmpty()){
			list = listOrgao.stream().map(uo -> uo.getId()).collect(Collectors.toList());
		}

		return list;
		
	}

	
}
