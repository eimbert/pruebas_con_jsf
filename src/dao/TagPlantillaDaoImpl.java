package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.TagPlantillaBO;

public class TagPlantillaDaoImpl implements TagPlantillaDao {

	
	@PersistenceContext(unitName = "PlantillasPU")
    EntityManager em;
	
	
	@Override
	public List<TagPlantillaBO> findAllPlantillas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagPlantillaBO findPlantillaById(TagPlantillaBO tagPlantilla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TagPlantillaBO findPlantillaByName(TagPlantillaBO tagPlantilla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertPlantilla(TagPlantillaBO tagPlantilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updatePlantilla(TagPlantillaBO tagPlantilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePlantilla(TagPlantillaBO tagPlantilla) {
		// TODO Auto-generated method stub

	}

}
