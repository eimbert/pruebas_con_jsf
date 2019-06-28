package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import domain.Documento;
import domain.PlantillaBO;

@Stateless
public class DocumentoDaoImpl implements DocumentoDao {

	@PersistenceContext(unitName = "PlantillasPU")
    EntityManager em;
	
	@Override
	public List<Documento> findAllDocuments() {
		return em.createNamedQuery("Documento.findAll").getResultList();
	}

	@Override
	public Documento findDocumentyId(int idDocumento) {
		 return em.find(Documento.class, idDocumento);
	}

	@Override
	public void insertDocument(Documento documento) {
		em.persist(documento);

	}

	@Override
	public void updateDocument(Documento documento) {
		em.merge(documento);

	}

	@Override
	public void deleteDocument(Documento documento) {
		em.merge(documento);
        em.remove(documento);

	}

}
