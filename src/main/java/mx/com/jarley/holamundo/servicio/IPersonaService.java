package mx.com.jarley.holamundo.servicio;

import java.util.List;
import mx.com.jarley.holamundo.domain.Persona;

public interface IPersonaService {
    
    
    public List<Persona> listarPersona();
    
    public void guardar(Persona persona);
    
    public void eliminar (Persona persona);
    
    public Persona buscarPersona(Persona persona);
    
    
    
}
