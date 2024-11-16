package pe.edu.vallegrande.product.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.product.model.ProductoModel;
import pe.edu.vallegrande.product.service.ProductoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Operation(summary = "Obtener todos los productos")
    @GetMapping
    public Flux<ProductoModel> getAllProductos() {
        return productoService.getAllProductos();
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public Mono<ProductoModel> getProductoById(@PathVariable Long id) {
        return productoService.getProductoById(id);
    }

    @Operation(summary = "Crear un nuevo producto")
    @PostMapping
    public Mono<ProductoModel> createProducto(@RequestBody ProductoModel producto) {
        return productoService.createProducto(producto);
    }

    @Operation(summary = "Actualizar un producto existente")
    @PutMapping("/{id}")
    public Mono<ProductoModel> updateProducto(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return productoService.updateProducto(id, producto);
    }


    // Agregado: Eliminar lógicamente un producto
    @Operation(summary = "Eliminar lógicamente un producto")
    @DeleteMapping("/logic/{id}")
    public Mono<ProductoModel> deleteLogicProducto(@PathVariable Long id) {
        return productoService.deleteLogicProducto(id);
    }


    // Restaurar un producto (cambiar su estado a "activo")
    @Operation(summary = "Restaurar un producto")
    @PutMapping("/restaurar/{id}")
    public Mono<ProductoModel> restoreProducto(@PathVariable Long id) {
        return productoService.restoreProducto(id);
    }


}
