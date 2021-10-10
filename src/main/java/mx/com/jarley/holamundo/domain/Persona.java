package mx.com.jarley.holamundo.domain;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;


import lombok.Generated;

@Data //para hacer  q se agregen getter y seter a esta clase hacerla beans
@Entity // volvemos la clase un entidad
@Table(name = "cliente") // no es ensesario pero asi podemos especificar la tabla que utilizara en l db esto pedira que se cree id
public class Persona implements Serializable{
    
    private static final long serialVersionUID=1L;
    // mapeo de la claveprimaria id_cliente en este caso
   
    @Id // identificamos q sera el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// la forma de generar la llave primaria indicando la estrategia
    private Long idCliente;
    
   @NotEmpty // esta notacion valida que el campo no sea null y q no este vacio
    private String cliente_nombre;
   
   @NotEmpty
    private String cliente_apellido;
   
   @NotEmpty
   @Email
    private String cliente_email;
   
  @NotEmpty
    private String cliente_telefono;
   
    private double cliente_saldo;
    
}
