package arquitetura.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import qualitativo.model.UnidadeOrcamentaria;

public class UoUtils {

	private UoUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Long> parseUO(Long unidadeOrcamentariaId, List<UnidadeOrcamentaria> listUnidadeOrcamentaria){
		
		List<Long> listUO =new ArrayList<>();
		
		if(unidadeOrcamentariaId!=null) {
			listUO.add(unidadeOrcamentariaId);
		}else if(listUnidadeOrcamentaria!=null && !listUnidadeOrcamentaria.isEmpty()){
			listUO = listUnidadeOrcamentaria.stream().map(uo -> uo.getId()).collect(Collectors.toList());
		}

		return listUO;
		
	}

	
}
