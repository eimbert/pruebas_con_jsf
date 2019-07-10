package dao;

import java.util.List;

import domain.PlantillaBaseBO;


public interface PlantillaBaseDao {
	public List<PlantillaBaseBO> findAllPlantillas();

	public PlantillaBaseBO findPlantillaById(int plantilla);

	public PlantillaBaseBO findPlantillaByName(PlantillaBaseBO plantilla);

	public void insertPlantilla(PlantillaBaseBO plantilla);

	public void updatePlantilla(PlantillaBaseBO plantilla);

	public void deletePlantilla(PlantillaBaseBO plantilla);
}
