package servicio.Interfaces;

import java.util.List;

import javax.ejb.Local;

import domain.PlantillaBaseBO;


public interface PlantillaBaseService {
	
    public List<PlantillaBaseBO> listarPlantillas();
    
    public PlantillaBaseBO encontrarPlantillaId(int plantilla);

    public PlantillaBaseBO encontrarPlantillaPorEmail(PlantillaBaseBO plantilla);

    public void registrarPlantilla(PlantillaBaseBO plantilla);

    public void modificarPlantilla(PlantillaBaseBO plantilla);

    public void eliminarPlantilla(PlantillaBaseBO plantilla);

}
