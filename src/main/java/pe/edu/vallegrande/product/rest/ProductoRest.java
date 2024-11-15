package pe.edu.vallegrande.product.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.product.model.ProductoModel;
import pe.edu.vallegrande.product.service.ProductoService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoRest {

    private final ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public Flux<ProductoModel> getAllProductos() {
        return productoService.getAllProductos();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Mono<ProductoModel> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public Mono<ProductoModel> createProducto(@RequestBody ProductoModel producto) {
        return productoService.createProducto(producto);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Mono<ProductoModel> updateProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProducto(id, producto);
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public Mono<Void> deleteProducto(@PathVariable Long id) {
        return productoService.deleteProducto(id);
    }
}
