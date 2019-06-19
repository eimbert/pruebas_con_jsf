package funcionesWord;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class WordGetTags {

	Map <String, List<Tag>> tags;
	WordTagsFunctions searchTags;
	
	public WordGetTags(String documentName, String inPath) throws FileNotFoundException, IOException {
		searchTags = new WordTagsFunctions(documentName, inPath);
		tags = new HashMap<String, List<Tag>>();
	}
	
	public void searchTags() throws FileNotFoundException, IOException {
		List <String> etiquetas = new ArrayList<String>();
		
		searchTags.openDocument();
		searchTags.SearchTagsInText(etiquetas);
		searchTags.searchTagsInTables(etiquetas);

		for(String str : etiquetas) {
			List <Tag> tmpTags = new ArrayList<Tag>();
			String codigoTag = str;
			String apartado = str.substring(2, str.indexOf(Constants.CODIGO_SEPARADOR, 2)); 
			String tipoCampo = str.substring(str.indexOf(Constants.CODIGO_SEPARADOR) + 1, str.indexOf(Constants.CODIGO_SEPARADOR,
											 str.indexOf(Constants.CODIGO_SEPARADOR) + 1)); 
			String txtSolicitud = str.substring(str.indexOf(Constants.CODIGO_SEPARADOR, str.indexOf(Constants.CODIGO_SEPARADOR)+1)+1, 
												str.indexOf(Constants.CODIGO_FINAL));
			if(tags.get(apartado) == null) {
				tmpTags.add(new Tag(codigoTag, tipoCampo, txtSolicitud));
				tags.put(apartado, tmpTags);			
			}else {
				tags.get(apartado).add(new Tag(codigoTag, tipoCampo, txtSolicitud));
			}
		}
		searchTags.closeDocument();
	}

	public Map<String, List<Tag>> getTags() {
		return tags;
	}

	public void setTags(Map<String, List<Tag>> tags) {
		this.tags = tags;
	}

	public WordTagsFunctions getSearchTags() {
		return searchTags;
	}

	public void setSearchTags(WordTagsFunctions searchTags) {
		this.searchTags = searchTags;
	}
	
	
}
