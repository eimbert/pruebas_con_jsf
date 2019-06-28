package servicio.Interfaces;

import java.util.List;

import domain.Documento;

public interface DocumentoService {

    public List<Documento> listarDocumentos();

    public void guardarDocumento(Documento documento);

    public void modificarDocumento(Documento documento);

    public void eliminarDocumento(Documento documento);
}
