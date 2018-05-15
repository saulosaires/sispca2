package relatorio.model;

import java.util.ArrayList;
import java.util.List;

public class Relatorio {

	private String label;

	private String link;

	private String name;

	private List<Relatorio> subRelatorio = new ArrayList<>();

	public Relatorio() {

	}

	public Relatorio(String label, String link,String name) {
		super();
		this.label = label;
		this.link = link;
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Relatorio> getSubRelatorio() {
		return subRelatorio;
	}

	public void setSubRelatorio(List<Relatorio> subRelatorio) {
		this.subRelatorio = subRelatorio;
	}

 

}
