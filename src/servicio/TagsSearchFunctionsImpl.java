package servicio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import domain.WordGetTags;
import funcionesWord.TagWord;


public class TagsSearchFunctionsImpl implements TagsSearchFunctions {

	WordGetTags wordTags = new WordGetTags();
	
	@Override
	public int searchTags(String path, String name) throws FileNotFoundException, IOException {
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
