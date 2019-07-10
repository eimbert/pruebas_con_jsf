package funcionesWord;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import domain.TagPlantillaBO;
import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;
import fr.opensagres.poi.xwpf.converter.core.XWPFConverterException;
import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;
import fr.opensagres.xdocreport.converter.ConverterRegistry;

import fr.opensagres.xdocreport.core.document.DocumentKind;

public class WordReplaceTags {

	private XWPFDocument document;
	private String inPath, outPath;
	private String documentName;
	private EliminarCodigo eliminarCodigo = new EliminarCodigo();

	public void replaceTags(List<TagPlantillaBO> tags) throws FileNotFoundException, IOException {
		openDocument();

		tags.forEach(object -> {
			replaceTagsFormat(object);
			replaceTextInTables(object);
		});

		eliminarCodigo.borrarCodigo(Constants.CODIGO_INICIO_PARRAFO);
		eliminarCodigo.borrarCodigo(Constants.CODIGO_FIN_PARRAFO);
		eliminarCodigo.escribirDocumento();
				
		saveDocument();
		closeDocument();
	}
	
	public void replaceTagsFormat(TagPlantillaBO tag) {
		String subTag = "";

		for (XWPFParagraph p : document.getParagraphs()) {
			List<XWPFRun> runs = p.getRuns();
			if (runs != null) {
				for (XWPFRun r : runs) {
					String txt = r.getText(0);
					subTag = concatenandoRuns(txt, tag, subTag, r);
					eliminarCodigo.anadirParrafo( new FragmentoDelDocumento(r));
				}
			}
		}
	}

	public void replaceTextInTables(TagPlantillaBO tag) {
		String subTag = "";

		for (XWPFTable tbl : document.getTables()) {
			for (XWPFTableRow row : tbl.getRows()) {
				for (XWPFTableCell cell : row.getTableCells()) {
					for (XWPFParagraph p : cell.getParagraphs()) {
						for (XWPFRun r : p.getRuns()) {
							String txt = r.getText(0);
							subTag = concatenandoRuns(txt, tag, subTag, r);
							eliminarCodigo.anadirParrafo( new FragmentoDelDocumento(r));
						}
					}
				}
			}
		}
	}

	private String concatenandoRuns(String txt, TagPlantillaBO tag, String subTag, XWPFRun r) {
		if (txt != null && txt.contains(Constants.CODIGO_INICIO) || !subTag.equals("")) {
			int inicio = txt.indexOf(Constants.CODIGO_INICIO);
			int fin = txt.indexOf(Constants.CODIGO_FINAL, inicio);
			
			
			if (fin > inicio && fin > 0 && inicio >= 0) {
				if (tag.getCodigoEtiqueta().equals(txt)) {
					r.setText("", 0); // Elimino el texto con el código de la etiqueta del documento.
					typeFieldResponse(tag.getRespuesta(), r, tag.getTipoDeCampo(), tag.getRespuesta());
					return "";
				}
			} else {
				subTag += txt;
				if ((txt.contains(Constants.CODIGO_FINAL) || txt.contains(Constants.CODIGO_CONTROL))
						&& !txt.contains(Constants.CODIGO_INICIO)) {
					r.setText("", 0); // Borro el último caracter
					if (tag.getCodigoEtiqueta().trim().equals(subTag.trim())) {
						typeFieldResponse(tag.getRespuesta(), r, tag.getTipoDeCampo(), tag.getRespuesta());
					} else {
						r.setText(subTag, 0); // Pongo la etiqueta original porque no es la que buscaba
					}
					return "";
				} else {
					r.setText("", 0); // Elimino el texto con el código de la etiqueta del documento.
				}
			}
		}
		return subTag;
	}

	private void typeFieldResponse(String str, XWPFRun r, String typeField, String respuesta) {
		r.setFontSize(11);

		switch (typeField) {
		case "text":
			r.setText(str);
			return;
		case "only_check":
			if("true".equals(respuesta))
				r.setText("[X]");
			else
				r.setText("[  ]");
			return;
		case "y_n":
			if("true".equals(respuesta))
				r.setText("Si[X] No[  ]");
			else
				r.setText("Si[  ] No[X]");
			return;
		}
	}
	

	public void setInPath(String inPath) {
		this.inPath = inPath;
	}

	public void setOutPath(String outPath) {
		this.outPath = outPath;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public void openDocument() throws FileNotFoundException, IOException {
		document = new XWPFDocument(new FileInputStream(inPath + "/" + documentName));
	}

	public void saveDocument() throws FileNotFoundException, IOException {
		FileOutputStream fileOutput = new FileOutputStream (outPath + "/" + documentName);
		document.write(fileOutput);
		fileOutput.close();
	}

	public void closeDocument() throws IOException {
		document.close();
	}
}
