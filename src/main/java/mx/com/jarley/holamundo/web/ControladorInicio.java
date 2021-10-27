package mx.com.jarley.holamundo.web;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import mx.com.jarley.holamundo.domain.Persona;
import mx.com.jarley.holamundo.servicio.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

// pasamos a ser un controlador mvc
//@RestController
@Controller
// como agregamos la libreria de lonbook podemos enviar informacion al loog
//tenemos que agregar la notacion de
@Slf4j
public class ControladorInicio {

    /*antes 
    @Autowired // para inyectar la dependencia  q es la interface
    private IPersonaDao personaDao;
     */
    // ahora 
    @Autowired
    private IPersonaService personaService;

    @GetMapping("/")
    public String inicio(Model model , @AuthenticationPrincipal User user) {
        // se puede mandar a consola el usuario q inicio seesin
        // ahora para recuperar infromacion
        var personas = personaService.listarPersona();
        log.info("usuario que inicio sesion: "+ user);
        log.info("ejecutando el servidor spring mvc");
        // para recuperacion de informacion basta con 
        // antes de persona service
        //var personas = personaDao.findAll(); // este recupera una lista de personas
         model.addAttribute("personas", personas); 
        
        var saldoTotal =0D;
        for(var p : personas){
        saldoTotal += p.getCliente_saldo();
                }
        model.addAttribute("saldoTotal", saldoTotal);
        model.addAttribute("totalClientes", personas.size());
        

// se manda la lista vacia para verificar que funcionen los if
//List <Persona> personas = new ArrayList<>(); 
      
       
        return "index";
    }

    @GetMapping("/agregar")
    public String agregar(Persona persona) {

        return "modificar";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Persona persona, Errors errores) { // se agrega para indicar q se validara el campo y por si llegan errores
        if (errores.hasErrors()) { // si llehan errores     
            return "modificar";
        }
        personaService.guardar(persona);
        return "redirect:/";
    }
    

    @GetMapping("/editar/{idCliente}")
    public String editar(Persona persona, Model model) {
        persona = personaService.buscarPersona(persona);
        model.addAttribute("persona", persona);
        return "modificar";

    }
    // esta foorma se usa al compartir el id cliente y la siguiente al agregar el id e el path
//            @GetMapping("/eliminar/{idCliente}")
//            public String eliminar(Persona persona, Model model){
//                personaService.eliminar(persona);
//                return "redirect:/";
//
//            }

    // aqui ya se le manda directo el id cliente por eso solo piuede ser de esa forma 
    @GetMapping("/eliminar")
    public String eliminar(Persona persona, Model model) {
        personaService.eliminar(persona);
        return "redirect:/";

    }
}
