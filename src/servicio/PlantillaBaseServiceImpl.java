package servicio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PlantillaBaseDao;
import domain.PlantillaBaseBO;
import servicio.Interfaces.PlantillaBaseService;

@Stateless
public class PlantillaBaseServiceImpl implements PlantillaBaseService {

	
	@Inject
	private PlantillaBaseDao plantillaBaseDao;
	
	@Override
	public List<PlantillaBaseBO> listarPlantillas() {
		return plantillaBaseDao.findAllPlantillas();		
	}
	
	@Override
	public PlantillaBaseBO encontrarPlantillaId(int plantilla) {
		return plantillaBaseDao.findPlantillaById(plantilla);
	}

	@Override
	public PlantillaBaseBO encontrarPlantillaPorEmail(PlantillaBaseBO plantilla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarPlantilla(PlantillaBaseBO plantilla) {
		plantillaBaseDao.insertPlantilla(plantilla);

	}

	@Override
	public void modificarPlantilla(PlantillaBaseBO plantilla) {
		plantillaBaseDao.updatePlantilla(plantilla);

	}

	@Override
	public void eliminarPlantilla(PlantillaBaseBO plantilla) {
		// TODO Auto-generated method stub

	}

}
