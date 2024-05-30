package webflux.estudio.web.domain.model.todo.gateway;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.estudio.web.domain.model.dto.Producto;

public interface EntityRepository {
    
    Flux<Producto> catalogo();
    Flux<Producto> catalogoByCategoria(String categoria);
    Mono<Producto> productoCodigo(int codigoProducto);
    Mono<Void> agregarProducto(Producto producto);
    Mono<Producto> elimiMonoProducto(int codigoProducto);
    Mono<Producto> modificarProducto(Producto producto);

}
