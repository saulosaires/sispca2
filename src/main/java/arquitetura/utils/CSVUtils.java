package arquitetura.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;

 
import qualitativo.model.Acao;
import qualitativo.model.Programa;

public class CSVUtils {
	
	private static final char DEFAULT_SEPARATOR = '|';
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void generateCsvFile(File arquivo,List<?> list, Class curClass){
//		try
//	    {
//			
//	        FileWriter writer = new FileWriter(arquivo, true);
//	        
//	        BufferedWriter bwa = new BufferedWriter(writer);
//	        
//	        DecimalFormat df = new DecimalFormat("0.00000");
//	        
//	        if(curClass.isAssignableFrom(Acao.class)){
//	        	bwa.append("ano"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("cod_acao"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_orcamento"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_acao"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_horizonte_temporal"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_forma_implementacao"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("objetivo");
//	 	        bwa.newLine();
//	        	for (Iterator<Acao> iterator = (Iterator<Acao>) list.iterator(); iterator.hasNext();) {
//					Acao ac = iterator.next();
//					bwa.append(ac.getExercicio().getAno() != null ? ac.getExercicio().getAno().toString() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getCodigo());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getTipoOrcamento().getDescricao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getTipoAcao().getDescricao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getTipoHorizonteTemporal().getDescricao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getTipoFormaImplementacao().getDescricao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ac.getFinalidade());
//			        bwa.newLine();
//				}
//	        }
//	        
//	        if(curClass.isAssignableFrom(Programa.class)){
//	        	bwa.append("cod_programa"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("objetivo"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("responsavel"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("indicador"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("publico_alvo"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("cod_tipo_programa"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_programa"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("problema"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipo_horizonte_temporal");
//	 	        bwa.newLine();	 	       
//	        	for (Iterator<Programa> iterator = (Iterator<Programa>) list.iterator(); iterator.hasNext();) {
//					Programa prog = iterator.next();
//					bwa.append(prog.getCodigo());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getObjetivo());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getResponsavel() != null ? prog.getResponsavel() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getIndicador() != null ? prog.getIndicador() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getPublicoAlvo()!= null ? prog.getPublicoAlvo() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getCodigoTipoPrograma() !=null ? prog.getCodigoTipoPrograma() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getTipoPrograma() != null ? prog.getTipoPrograma() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getProblema() != null ? prog.getProblema() : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(prog.getTipoHorizonteTemporal() != null ? prog.getTipoHorizonteTemporal() : "");
//			        bwa.newLine();			       
//				}
//	        }
//	        
//	        if(curClass.isAssignableFrom(ElaboracaoMetaFisica.class)){
//	        	bwa.append("ano"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codProg"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codAcao"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codUo"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("produto"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipoCalcMet"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codUnidMed"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("unid_med"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("qtd_meta_prev"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("qtd_meta_prev_lei");bwa.append(DEFAULT_SEPARATOR); 
//	 	        bwa.newLine();
//	        	for (Iterator<ElaboracaoMetaFisica> iterator = (Iterator<ElaboracaoMetaFisica>) list.iterator(); iterator.hasNext();) {
//	        		ElaboracaoMetaFisica meta = iterator.next();
//					bwa.append(meta.getAno().toString());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getCodPrograma());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getCodAcao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getCodUo());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getProdAcao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getTipoCalculoMeta());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getCodUnidMed() != null? meta.getCodUnidMed().toString(): "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getUnidadeMed());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getQtdMetaFisicaPrev() != null? df.format(meta.getQtdMetaFisicaPrev()) : "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(meta.getQtdMetaFisicaPrevLei() != null? df.format(meta.getQtdMetaFisicaPrevLei()) : "");			        
//			        bwa.newLine();
//			       
//				}
//	        }
//	        
//	        if(curClass.isAssignableFrom(ExecucaoMetaFisica.class)){
//	        	bwa.append("mes"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("ano"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codProg"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codAcao"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("codUo"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("produto"); bwa.append(DEFAULT_SEPARATOR);
//	 	        bwa.append("tipoCalcMet"); bwa.append(DEFAULT_SEPARATOR);
//	 	       	bwa.append("codUnidMed"); bwa.append(DEFAULT_SEPARATOR);
//	 	       	bwa.append("unid_med"); bwa.append(DEFAULT_SEPARATOR);
//	 	     	bwa.append("qtd_executada"); 
//	 	        bwa.newLine();
//	        	for (Iterator<ExecucaoMetaFisica> iterator = (Iterator<ExecucaoMetaFisica>) list.iterator(); iterator.hasNext();) {
//					ExecucaoMetaFisica ex = iterator.next();
//					bwa.append(ex.getMes().toString());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getAno().toString());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getCodPrograma());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getCodAcao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getCodUo());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getProdAcao());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getTipoCalculoMeta());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getCodUnidMed() != null? ex.getCodUnidMed().toString(): "");bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getUnidadeMed());bwa.append(DEFAULT_SEPARATOR);
//			        bwa.append(ex.getQtdExecutada() != null? df.format(ex.getQtdExecutada()) : "");
//			        bwa.newLine();
//				}
//	        }
//
//	        bwa.close();
//	        writer.close();
//	    }
//	    catch(IOException e)
//	    {
//	         e.printStackTrace();
//	         System.out.println("Erro ao gerar arquivo!");
//	    } 
	}
	
}
