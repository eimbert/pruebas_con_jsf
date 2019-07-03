package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import funcionesWord.TagWord;
import funcionesWord.WordGetTags;
import servicio.Interfaces.TagsSearchFunctions;

@Stateless
public class TagsSearchFunctionsImpl implements TagsSearchFunctions {

	WordGetTags wordTags = new WordGetTags();
			
	
	@Override
	public int searchTags(String path, String name) throws FileNotFoundException, IOException, InvalidFormatException {
		wordTags.setInPath(path);
		wordTags.setDocumentName(name);
		wordTags.searchTags();
		return 0;
	}

	@Override
	public Map<String, List<TagWord>> getTags() {
		return  wordTags.getTags();
	}

}
