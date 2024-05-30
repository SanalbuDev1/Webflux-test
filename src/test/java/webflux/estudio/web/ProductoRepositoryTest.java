package webflux.estudio.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;
import webflux.estudio.web.domain.model.dto.Producto;
import webflux.estudio.web.infraestructure.entrypoint.ProductoRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductoRepositoryTest {

    private ProductoRepository productoRepository;

    @BeforeEach
    public void setUp() {
        productoRepository = new ProductoRepository();
    }

    @Test
    public void testCatalogo() {
        StepVerifier.create(productoRepository.catalogo())
            .expectNextCount(5)
            .verifyComplete();
    }

    @Test
    public void testCatalogoByCategoria() {
        StepVerifier.create(productoRepository.catalogoByCategoria("Categoria1"))
            .assertNext(producto -> assertThat(producto.getCategoria()).isEqualTo("Categoria1"))
            .verifyComplete();
    }

    @Test
    public void testProductoCodigo() {
        StepVerifier.create(productoRepository.productoCodigo(1))
            .assertNext(producto -> assertThat(producto.getCodigoProducto()).isEqualTo(1))
            .verifyComplete();
    }

    @Test
    public void testAgregarProducto() {
        Producto nuevoProducto = new Producto(6, "Producto 6", "Categoria6", 600.0, 60);
        StepVerifier.create(productoRepository.agregarProducto(nuevoProducto))
            .verifyComplete();

        StepVerifier.create(productoRepository.productoCodigo(6))
            .assertNext(producto -> assertThat(producto).isEqualTo(nuevoProducto))
            .verifyComplete();
    }

    @Test
    public void testElimiMonoProducto() {
        StepVerifier.create(productoRepository.elimiMonoProducto(1))
            .assertNext(producto -> assertThat(producto.getCodigoProducto()).isEqualTo(1))
            .verifyComplete();

        StepVerifier.create(productoRepository.productoCodigo(1))
            .verifyComplete();
    }

    @Test
    public void testModificarProducto() {
        Producto productoModificado = new Producto(1, "Producto Modificado", "Categoria Modificada", 1000.0, 100);
        StepVerifier.create(productoRepository.modificarProducto(productoModificado))
            .assertNext(producto -> assertThat(producto).isEqualTo(productoModificado))
            .verifyComplete();
    }
}