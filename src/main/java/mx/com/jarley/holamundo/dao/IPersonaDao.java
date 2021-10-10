package mx.com.jarley.holamundo.dao;

import mx.com.jarley.holamundo.domain.Persona;
import org.springframework.data.repository.CrudRepository;

// extendiendo de grud  esto hace que se agregen los metodos de crear,leer,actualizar y borrar ala interface
// se tiene que especificar que tipo de elementos usara  y que tipo sera la llave primaria
public interface IPersonaDao extends CrudRepository<Persona, Long> {
    
    // se pueden agregar  mas firmas de metodos adicionales o personalizadas 
    // que sean diferentes a CRUD
    
}
