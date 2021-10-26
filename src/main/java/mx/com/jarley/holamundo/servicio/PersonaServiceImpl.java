package mx.com.jarley.holamundo.servicio;

import java.util.List;
import mx.com.jarley.holamundo.dao.IPersonaDao;
import mx.com.jarley.holamundo.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // para que spring reconosca como servicio  y podamos inyectarla como una implementacion de la interface 
public class PersonaServiceImpl implements IPersonaService {
    
    @Autowired // inyeccion de persona dao
    private IPersonaDao personaDao;
    
    // se deven declarar las transacciones para hacer rollback en caso de erro y commit en caso de q todo salga bien 
    // dependiendo el caso se agrega  el tipo de traccion
    
    @Override
    @Transactional (readOnly = true)// como solo leemos datos usamos esta notacion
    public List<Persona> listarPersona() {
        
return (List<Persona>) personaDao.findAll();
    }

    @Override
    @Transactional // como estos metodos si modifican la base se deve declarar asi sin paramentros
    public void guardar(Persona persona) {
        
        personaDao.save(persona);

    }

    @Override
    @Transactional
    public void eliminar(Persona persona) {
        personaDao.delete(persona);
    }

    @Override
    @Transactional(readOnly = true) // se declara y se agrega read ya que solo leera informacion    
    public Persona buscarPersona(Persona persona) {
     
        //regresara null si no lo encuentra
           return   personaDao.findById(persona.getIdCliente()).orElse(null);

    }



}
