package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import javax.ejb.Stateless;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import funcionesWord.v2.BuscarCamposEnDocumento;
import funcionesWord.v2.Constants;
import funcionesWord.v2.TagWord;
import servicio.Interfaces.TagsSearchFunctions; 

@Stateless
public class TagsSearchFunctionsImpl implements TagsSearchFunctions {

	BuscarCamposEnDocumento documento = new BuscarCamposEnDocumento();
	
	@Override
	public int searchTags(String path, String name) throws FileNotFoundException, IOException, InvalidFormatException {
	
		documento.buscarLasEtiquetas(Constants.CODIGO_INICIO, Constants.CODIGO_FINAL);
		
		return 0; //TODO devolver el número de campos encontrados para la tipología
	}

	@Override
	public List<TagWord> getTags() {
		return  documento.getEtiquetas();
	}

}
