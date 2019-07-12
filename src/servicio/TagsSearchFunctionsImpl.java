package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import funcionesWord.WordGetTags;
import funcionesWord.v2.BuscarCamposEnDocumento;

import funcionesWord.v2.TagWord;
import servicio.Interfaces.TagsSearchFunctions;
import funcionesWord.Constants; 

@Stateless
public class TagsSearchFunctionsImpl implements TagsSearchFunctions {

	WordGetTags wordTags = new WordGetTags();
			
	
	@Override
	public int searchTags(String path, String name) throws FileNotFoundException, IOException, InvalidFormatException {
		
		BuscarCamposEnDocumento documento = new BuscarCamposEnDocumento();

		documento.tratarDocumento(Constants.CODIGO_INICIO, Constants.CODIGO_FINAL);

		return 0;
	}

	@Override
	public Map<String, List<TagWord>> getTags() {
		return  wordTags.getTags();
	}

}
