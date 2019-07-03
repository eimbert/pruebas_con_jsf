package funcionesWord;

import org.apache.poi.xwpf.usermodel.XWPFRun;


public class DocumentWordFragment {

	private XWPFRun parrafo;
	private String textoParrafo;
	
	DocumentWordFragment(XWPFRun parrafo){
		this.parrafo = parrafo;
		this.textoParrafo = parrafo.getText(0);
	}
	
	public String getPrimerCatacter() {
		if(textoParrafo.length() > 0)
			return textoParrafo.substring(0,1);
		else
			return "";
	}
	
	public String getUltimoCarcater() {
		if(textoParrafo.length() > 0)
			return textoParrafo.substring(textoParrafo.length()-1);
		else
			return "";
	}
	
	public String getTextoParrafo() {
		return this.textoParrafo;
	}
	
	public void setTexto(String texto) {
		this.textoParrafo = texto;
	}
	
	public void escribirParrafo() {
		parrafo.setText(textoParrafo, 0);
	}
	
	public void borrarParrafo() {
		parrafo.setText("", 0);
	}
}
