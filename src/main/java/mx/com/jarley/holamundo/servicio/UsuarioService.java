package mx.com.jarley.holamundo.servicio;

import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import mx.com.jarley.holamundo.dao.UsuarioDao;
import mx.com.jarley.holamundo.domain.Rol;
import mx.com.jarley.holamundo.domain.Usuario;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Slf4j
public class UsuarioService  implements UserDetailsService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Usuario usuario = usuarioDao.findByUsername(username);
       
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        
        var roles = new ArrayList <GrantedAuthority>();
        
        for (Rol rol: usuario.getRoles()) {
            
            roles.add(new SimpleGrantedAuthority(rol.getNombre()));
        }
        return  new User(usuario.getUsername(), usuario.getPassword(),roles);
    }

    
   
    
    
    
}
