package pe.edu.vallegrande.dni.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pe.edu.vallegrande.dni.model.DniModel;
import pe.edu.vallegrande.dni.service.DniService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v1/dni")
public class ProductoController {

    @Autowired
    private DniService DniService;

    @Operation(summary = "Obtener todos los datos")
    @GetMapping
    public Flux<DniModel> getAllDni() {
        return productoService.getAllDni();
    }

    @Operation(summary = "Obtener un producto por ID")
    @GetMapping("/{id}")
    public Mono<DniModel> getDniById(@PathVariable Long id) {
        return productoService.getDniById(id);
    }

    @Operation(summary = "Crear un nuevo dato")
    @PostMapping
    public Mono<DniModel> createDni(@RequestBody DniModel dni) {
        return DniService.createDni(producto);
    }

    @Operation(summary = "Actualizar un dato existente")
    @PutMapping("/{id}")
    public Mono<DniModel> updateDni(@PathVariable Long id, @RequestBody DniModel dni) {
        return DniService.updateDni(id, producto);
    }


    // Agregado: Eliminar lógicamente un producto
    @Operation(summary = "Eliminar lógicamente un dato")
    @DeleteMapping("/logic/{id}")
    public Mono<DniModel> deleteLogicDni(@PathVariable Long id) {
        return DniService.deleteLogicDni(id);
    }


    // Restaurar un producto (cambiar su estado a "activo")
    @Operation(summary = "Restaurar un dato")
    @PutMapping("/restaurar/{id}")
    public Mono<DniModel> restoreDni(@PathVariable Long id) {
        return dniService.restoreDni(id);
    }


}
