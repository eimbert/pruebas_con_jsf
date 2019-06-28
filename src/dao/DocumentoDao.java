package dao;

import java.util.List;

import domain.Documento;


public interface DocumentoDao {

	public List<Documento> findAllDocuments();

	public Documento findDocumentyId(int documento);

	public void insertDocument(Documento documento);

	public void updateDocument(Documento documento);

	public void deleteDocument(Documento documento);
}
