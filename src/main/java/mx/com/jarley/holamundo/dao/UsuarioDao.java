package mx.com.jarley.holamundo.dao;

import mx.com.jarley.holamundo.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;



    public interface UsuarioDao extends JpaRepository<Usuario, Long> {
        // metodo para configurar seguridad
        //
        Usuario  findByUsername(String username);
    
}
