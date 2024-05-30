package webflux.estudio.web.domain.usecase;

import ch.qos.logback.classic.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import reactor.core.publisher.*;
import webflux.estudio.web.domain.model.dto.Producto;
import webflux.estudio.web.domain.model.todo.gateway.EntityRepository;

@RequiredArgsConstructor
@Log
public class ProductoRepositoryUseCase{    
 
    private final EntityRepository entityRepository;

    public Flux<Producto> catalogo() {
        log.info("antes de ingresar a catalogo");
        return entityRepository.catalogo().doOnNext(producto -> log.info(producto.toString()));        
    }

    public Flux<Producto> catalogoByCategoria(String categoria) {
        return entityRepository.catalogoByCategoria(categoria);
    }

    public Mono<Producto> productoCodigo(int codigoProducto) {
        return entityRepository.productoCodigo(codigoProducto);
    }

    public Mono<Void> agregarProducto(Producto producto) {
        return entityRepository.agregarProducto(producto);
    }

    public Mono<Producto> elimiMonoProducto(int codigoProducto) {
        return entityRepository.elimiMonoProducto(codigoProducto);
    }

    public Mono<Producto> modificarProducto(Producto producto) {
        return entityRepository.modificarProducto(producto);
    }

}
