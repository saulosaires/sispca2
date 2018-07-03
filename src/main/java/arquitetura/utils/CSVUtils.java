package arquitetura.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

import monitoramento.model.Execucao;
import qualitativo.model.Acao;
import qualitativo.model.Programa;

public class CSVUtils {
	
	private static final char DEFAULT_SEPARATOR = '|';
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void generateCsvFile(File arquivo,List<?> list, String type){
		try
	    {
			
	        FileWriter writer = new FileWriter(arquivo, true);
	        
	        BufferedWriter bwa = new BufferedWriter(writer);
	        
	        DecimalFormat df = new DecimalFormat("0.00000");
	      
	        if(  "Acao".equals(type)){
	        	bwa.append("ano"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("cod_acao"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_orcamento"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_acao"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_horizonte_temporal"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_forma_implementacao"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("objetivo");
	 	        bwa.newLine();
	 	        
	        	for (Iterator<Acao> iterator = (Iterator<Acao>) list.iterator(); iterator.hasNext();) {
					Acao ac = iterator.next();
					bwa.append(ac.getExercicio().getAno() != null ? ac.getExercicio().getAno().toString() : "");bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getCodigo());bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getTipoOrcamento().getDescricao());bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getTipoAcao().getDescricao());bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getTipoHorizonteTemporal().getDescricao());bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getTipoFormaImplementacao().getDescricao());bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ac.getFinalidade());
			        bwa.newLine();
				}
	        	
	        }
	        
	        if("Programa".equals(type)){
	        	bwa.append("cod_programa"); 	 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("objetivo"); 		 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("responsavel"); 		 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("indicador"); 		 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("publico_alvo"); 	 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("cod_tipo_programa"); bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_programa"); 	 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("problema"); 		 bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipo_horizonte_temporal");
	 	        bwa.newLine();	 	       
	        	for (Iterator<Programa> iterator = (Iterator<Programa>) list.iterator(); iterator.hasNext();) {
					Programa prog = iterator.next();
					bwa.append(prog.getCodigo());															bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getObjetivo());															bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getResponsavel() != null ? prog.getResponsavel() : "");					bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getIndicadorDesempenho() != null ? prog.getIndicadorDesempenho() : ""); bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getPublicoAlvo()!= null ? prog.getPublicoAlvo() : "");					bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getTipoPrograma() !=null ?  prog.getTipoPrograma().getCodigo() : "");	bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getTipoPrograma() != null ? prog.getTipoPrograma().getDescricao() : "");bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(prog.getProblema() != null ? prog.getProblema() : "");						bwa.append(DEFAULT_SEPARATOR); 
			        bwa.append(prog.getTipoHorizonteTemporal() != null ? prog.getTipoHorizonteTemporal().getDescricao() : "");
			        
			        bwa.newLine();			       
				}
	        }
	        
	        if("ElaboracaoMetaFisica".equals(type)){
	        	bwa.append("ano"); 				bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codProg"); 			bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codAcao"); 			bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codUo"); 			bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("produto"); 			bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipoCalcMet"); 		bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codUnidMed"); 		bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("unid_med"); 		bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("qtd_meta_prev");    bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("qtd_meta_prev_lei");bwa.append(DEFAULT_SEPARATOR); 
	 	        bwa.newLine();
	        	for (Iterator<Acao> iterator = (Iterator<Acao>) list.iterator(); iterator.hasNext();) {
	        		Acao acao = iterator.next();
	        		
					bwa.append(acao.getExercicio().getAno().toString());											bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getPrograma().getCodigo());														bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getCodigo());																	bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getUnidadeOrcamentaria().getCodigo());											bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getProduto());														  			bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getTipoCalculoMeta()!=null? acao.getTipoCalculoMeta().getDescricao():"");	    bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getUnidadeMedida() != null? acao.getUnidadeMedida().getCodigo().toString(): "");bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getUnidadeMedida() != null? acao.getUnidadeMedida().getDescricao(): "");		bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getQuantidadePlanejado().toString());											bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(acao.getQuantidadePlanejado().toString());	
			        
			        bwa.newLine();
			       
				}
	        }
	        
	        if("ExecucaoMetaFisica".equals(type)){
	        	bwa.append("mes");         bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("ano"); 		   bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codProg");     bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codAcao");     bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("codUo"); 	   bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("produto"); 	   bwa.append(DEFAULT_SEPARATOR);
	 	        bwa.append("tipoCalcMet"); bwa.append(DEFAULT_SEPARATOR);
	 	       	bwa.append("codUnidMed");  bwa.append(DEFAULT_SEPARATOR);
	 	       	bwa.append("unid_med");    bwa.append(DEFAULT_SEPARATOR);
	 	     	bwa.append("qtd_executada"); 
	 	        bwa.newLine();
	 	        
	        	for (Iterator<Execucao> iterator = (Iterator<Execucao>) list.iterator(); iterator.hasNext();) {
	        		Execucao ex = iterator.next();
					bwa.append(ex.getMes().getNumeroMes().toString());															   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getExercicio().getAno().toString());														       bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getPrograma().getCodigo());															   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getCodigo());			   															   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getUnidadeOrcamentaria().getCodigo());												   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getProduto());																 		   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getTipoCalculoMeta().getDescricao());												   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getUnidadeMedida()!= null? ex.getAcao().getUnidadeMedida().getCodigo().toString(): "");bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getAcao().getUnidadeMedida()!= null? ex.getAcao().getUnidadeMedida().getDescricao(): "");		   bwa.append(DEFAULT_SEPARATOR);
			        bwa.append(ex.getQuantidade() != null? df.format(ex.getQuantidade()) : "");
			        bwa.newLine();
				}
	        }

	        bwa.close();
	        writer.close();
	    }
	    catch(IOException e)
	    {
	         e.printStackTrace();
	         System.out.println("Erro ao gerar arquivo!");
	    } 
		
		
	}
	
}
