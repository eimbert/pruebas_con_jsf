package beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class Plantilla implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManagedProperty(value="Documento plantilla")
	private String name;
	private String fileName;

	public Plantilla() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
