package servicio.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import funcionesWord.v2.TagWord;

@Local
public interface TagsSearchFunctions {
	
	public int searchTags(String path, String name) throws FileNotFoundException, IOException, InvalidFormatException;
	public Map<String, List<TagWord>> getTags();
	
}
