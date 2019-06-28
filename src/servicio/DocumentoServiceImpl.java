package servicio;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import dao.DocumentoDao;
import dao.PlantillaDao;
import domain.Documento;
import servicio.Interfaces.DocumentoService;

@Stateless
public class DocumentoServiceImpl implements DocumentoService {

	@Inject
	private DocumentoDao documentoDao;
	
	@Override
	public List<Documento> listarDocumentos() {
		return documentoDao.findAllDocuments();
	}

	@Override
	public void guardarDocumento(Documento documento) {
		documentoDao.insertDocument(documento);

	}

	@Override
	public void modificarDocumento(Documento documento) {
		documentoDao.updateDocument(documento);

	}

	@Override
	public void eliminarDocumento(Documento documento) {
		documentoDao.deleteDocument(documento);

	}

}
