package servicio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.PlantillaDao;
import domain.PlantillaBO;

@Stateless
public class PlantillaServiceImpl implements PlantillaService {

	
	@Inject
	private PlantillaDao plantillaDao;
	
	@Override
	public List<PlantillaBO> listarPlantillas() {
		return plantillaDao.findAllPlantillas();
	}

	@Override
	public PlantillaBO encontrarPlantillaId(int plantilla) {
		return plantillaDao.findPlantillaById(plantilla);
	}

	@Override
	public PlantillaBO encontrarPlantillaPorEmail(PlantillaBO plantilla) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void registrarPlantilla(PlantillaBO plantilla) {
		plantillaDao.insertPlantilla(plantilla);

	}

	@Override
	public void modificarPlantilla(PlantillaBO plantilla) {
		// TODO Auto-generated method stub

	}

	@Override
	public void eliminarPlantilla(PlantillaBO plantilla) {
		// TODO Auto-generated method stub

	}

}
