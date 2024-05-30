package webflux.estudio.web.infraestructure.entrypoint;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import webflux.estudio.web.domain.model.dto.Producto;
import webflux.estudio.web.domain.model.todo.gateway.EntityRepository;

@Repository
public class ProductoRepository implements EntityRepository{

      List<Producto> listaProductosMock = new ArrayList<>(
        List.of(
            new Producto(1, "Producto 1", "Categoria1", 100.0, 10),
            new Producto(2, "Producto 2", "Categoria2", 200.0, 20),
            new Producto(3, "Producto 3", "Categoria3", 300.0, 30),
            new Producto(4, "Producto 4", "Categoria4", 400.0, 40),
            new Producto(5, "Producto 5", "Categoria5", 500.0, 50)
        )
    );    

    public Flux<Producto> catalogo() {

        // como List es una interfaz que herada de Collection, se puede usar el metodo fromIterable
        // devuelve un Flux<producto> a partir de una lista de productos
        return Flux.fromIterable(listaProductosMock)
        .delayElements(Duration.ofSeconds(1));

    }

    public Flux<Producto> catalogoByCategoria(String categoria) {

        // devuelve un Flux<producto> a partir de una lista de productos
        // filtrando por la categoria
        return Flux.fromIterable(listaProductosMock)
            .filter(producto -> producto.getCategoria().equals(categoria));
    }

    public Mono<Producto> productoCodigo(int codigoProducto) {
            
        // devuelve un Mono<producto> a partir de una lista de productos
        // filtrando por el codigo del producto
        // si sucede un error de negocio, se lanza una excepcion

            return Flux.fromIterable(listaProductosMock)
                .filter(producto -> producto.getCodigoProducto() == codigoProducto)
                .next();
         
    
    }

    public Mono<Void> agregarProducto(Producto producto) {
           
            // devuelve un Mono<Void> a partir de una lista de productos
            // si sucede un error de negocio, se lanza una excepcion
            // se agrega el producto a la lista de productos
        
            return productoCodigo(producto.getCodigoProducto())
            .switchIfEmpty(Mono.just(producto)
            .map(x -> {
                listaProductosMock.add(x);
                System.out.println("Producto agregado" + x);
                return x;
            }))
            .then();
        }

  
    public Mono<Producto> elimiMonoProducto(int codigoProducto) {

        // devuelve un Mono<producto> a partir de una lista de productos
        // filtrando por el codigo del producto
        // si sucede un error de negocio, se lanza una excepcion
        // se elimina el producto de la lista de productos

        return productoCodigo(codigoProducto)
            //.doOnNext(lista -> listaProductosMock.remove(lista));
            .doOnNext(listaProductosMock::remove);
    }

    public Mono<Producto> modificarProducto(Producto producto) {

        // devuelve un Mono<producto> a partir de una lista de productos
        // filtrando por el codigo del producto
        // si sucede un error de negocio, se lanza una excepcion
        // se modifica el producto de la lista de productos

        return productoCodigo(producto.getCodigoProducto())
            .flatMap( p -> {
                p.setNombre(producto.getNombre());
                p.setPrecioUnitario(producto.getPrecioUnitario());
                p.setCategoria(producto.getCategoria());
                p.setStock(producto.getStock());
                return Mono.just(p);
            });

    }
    
}
