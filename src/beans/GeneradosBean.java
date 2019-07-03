package beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import funcionesWord.Constants;
import funcionesWord.ToPDF;
import domain.Documento;
import lombok.Getter;
import lombok.Setter;
import servicio.Interfaces.DocumentoService;


@Getter
@Setter
@ManagedBean
@ViewScoped
public class GeneradosBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private DocumentoService documentoService;
	
	private List<Documento> documentos;
	private Documento selectedDcoumento;
	private String realPath;

	@PostConstruct
    public void init() {
		documentos = documentoService.listarDocumentos();
		realPath=null;
		
	}

	public String booleanToString(Boolean valor){
		if(valor)
			return "Sí";
		else
			return "No";
	}
	
	public void onRowEdit(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Guardando cambios", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		documentoService.modificarDocumento((Documento) event.getObject());
	}
	
	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edición cancelada", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
    public void onRowSelect(SelectEvent event) throws InterruptedException, ExecutionException, IOException  {
    	String documento = ((Documento) event.getObject()).getNombre();
    	documento = documento.replace("docx", "pdf");
    	generarPdf(documento);
    	realPath = Constants.OUT_PATH_PDF + documento;
        FacesMessage msg = new FacesMessage("Document Selected", ((Documento) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);        
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Document Unselected", ((Documento) event.getObject()).getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void generarPdf(String name) throws InterruptedException, ExecutionException, IOException {
    	ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	
    	File fichero = new File(servletContext.getRealPath("/")+Constants.OUT_PATH_PDF + name);
    	
    	
    	if (!fichero.exists()) {
    		String nameOrigin = name.replace("pdf", "docx");
			ToPDF.toPdf_d4j(Constants.OUT_PATH + "\\" + nameOrigin, servletContext.getRealPath("/")+Constants.OUT_PATH_PDF + name, servletContext.getRealPath("/")+Constants.OUT_PATH_PDF );
			
    	}
    }
    
}
