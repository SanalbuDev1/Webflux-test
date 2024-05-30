package webflux.estudio.web.infraestructure.inputadapters.http;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.estudio.web.domain.model.dto.Producto;
import webflux.estudio.web.domain.usecase.ProductoRepositoryUseCase;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequiredArgsConstructor
public class ProductoApi {
    
    private final ProductoRepositoryUseCase productoUseCase;

    @GetMapping("/catalogo")
    public ResponseEntity<Flux<Producto>> getMethodName() {
        return new ResponseEntity<Flux<Producto>>(productoUseCase.catalogo(), HttpStatus.OK);
    }

    @GetMapping("/catalogoByCategoria/{categoria}")
    public ResponseEntity<Flux<Producto>> catalogoByCategoria(@PathVariable("categoria") String categoria){
        return new ResponseEntity<Flux<Producto>>(productoUseCase.catalogoByCategoria(categoria), HttpStatus.OK);
    }

    @GetMapping("/productoCodigo/{codigoProducto}")
    public ResponseEntity<Mono<Producto>> productoCodigo(@PathVariable("codigoProducto") int codigoProducto) {
        return new ResponseEntity<Mono<Producto>>(productoUseCase.productoCodigo(codigoProducto), HttpStatus.OK);
        
    }

    @PostMapping("/agregarProducto")
    public ResponseEntity<Mono<Void>> agregarProducto(@RequestBody Producto producto) {
        return new ResponseEntity<Mono<Void>>(productoUseCase.agregarProducto(producto), HttpStatus.OK);
    }

    @DeleteMapping("/eliminarProducto/{codigo}")
    public Mono<ResponseEntity<Producto>> eliminarProducto(@PathVariable("codigo") int codigoProducto) {
        return productoUseCase.elimiMonoProducto(codigoProducto)
        .map(p-> {
            return new ResponseEntity<Producto>(p, HttpStatus.OK);            
        })
        .switchIfEmpty(Mono.just(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND)));
    }

    @PutMapping("/modificarProducto")
    public Mono<ResponseEntity<Producto>> modificarProducto(@RequestBody Producto producto) {
        return productoUseCase.modificarProducto(producto)
        .map(p-> {
            return new ResponseEntity<Producto>(p, HttpStatus.OK);            
        })
        .switchIfEmpty(Mono.just(new ResponseEntity<Producto>(HttpStatus.NOT_FOUND)));
    }
    

}
