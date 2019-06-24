package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import domain.PlantillaBO;

@Stateless
public class PlantillaDaoImpl implements PlantillaDao {

	@PersistenceContext(unitName = "PlantillasPU")
    EntityManager em;
	
	@Override
	public List<PlantillaBO> findAllPlantillas() {
		 return em.createNamedQuery("Plantilla.findAll").getResultList();
	}

	@Override
	public PlantillaBO findPlantillaById(int plantilla) {
		 return em.find(PlantillaBO.class, plantilla);
	}

	@Override
	public PlantillaBO findPlantillaByName(PlantillaBO plantilla) {
        Query query = em.createQuery("from Plantilla p where p.nombre =: name");
        query.setParameter("name", plantilla.getNombre());
        return (PlantillaBO) query.getSingleResult();
	}

	@Override
	public void insertPlantilla(PlantillaBO plantilla) {
		em.persist(plantilla);
	}

	@Override
	public void updatePlantilla(PlantillaBO plantilla) {
		 em.merge(plantilla);

	}

	@Override
	public void deletePlantilla(PlantillaBO plantilla) {
        em.merge(plantilla);
        em.remove(plantilla);

	}

}
