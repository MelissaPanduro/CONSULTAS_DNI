package pe.edu.vallegrande.dni.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import pe.edu.vallegrande.dni.model.ProductoModel;
import pe.edu.vallegrande.dni.service.ProductoService;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/dni")
@RequiredArgsConstructor
public class ProductoRest {

    private final DniService dniService;

    // Obtener todos los productos
    @GetMapping
    public Flux<DniModel> getAllDni() {
        return dniService.getAllDni();
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public Mono<DniModel> getDniById(@PathVariable Long id) {
        return dniService.getDniById(id);
    }

    // Crear un nuevo producto
    @PostMapping
    public Mono<DniModel> createDni(@RequestBody DniModel dni) {
        return dniService.createDni(dni);
    }

    // Actualizar un producto existente
    @PutMapping("/{id}")
    public Mono<DniModel> updateDni(@PathVariable Long id, @RequestBody ProductoModel producto) {
        return dniService.updateDni(id, producto);
    }

    // Eliminar un producto de forma lógica
    @DeleteMapping("/{id}")
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
