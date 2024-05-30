package webflux.estudio.web.domain.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    
    private int codigoProducto;
    private String nombre;
    private String categoria;
    private double precioUnitario;
    private int stock;

}
