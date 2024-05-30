package webflux.estudio.web.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
@ToString
public class Persona {    

    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    
}
