package pe.edu.vallegrande.dni.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.dni.model.ProductoModel;
import pe.edu.vallegrande.dni.repository.ProductoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DniService {

    private final DniRepository dniRepository;

    @Autowired
    public DniService(DniRepository dniRepository) {
        this.dniRepository = dniRepository;
    }

    /**
     * Obtener todos los productos activos
     * @return Flux<ProductoModel> lista de productos activos
     */
    public Flux<DniModel> getAllDni() {
        return productoRepository.findByEstado("activo"); // Solo productos con estado "activo"
    }

    /**
     * Obtener un producto por su ID
     * @param id ID del producto
     * @return Mono<ProductoModel> producto encontrado o vacío si no existe
     */
    public Mono<ProductoModel> getProductoById(Long id) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)));
    }

    /**
     * Crear un nuevo producto
     * @param producto producto a crear
     * @return Mono<ProductoModel> producto creado
     */
    public Mono<ProductoModel> createProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualizar un producto existente
     * @param id ID del producto a actualizar
     * @param producto datos del producto actualizado
     * @return Mono<ProductoModel> producto actualizado
     */
    public Mono<ProductoModel> updateProducto(Long id, ProductoModel producto) {
        return productoRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Producto no encontrado con ID: " + id)))
                .flatMap(existingProducto -> {
                    // Actualiza los campos del producto existente
                    existingDni.setSuccess(dni.getSuccess());
                    existingDni.setDni(dni.getDni());
                    existingDni.setNombres(dni.getNombres());
                    existingDni.setApellidoPaterno(dni.getApellidoPaterno());
                    existingDni.setApellidoMaterno(dni.getApellidoMaterno());
                    existingDni.setCodVerifica(dni.getCodVerifica());
                    return productoRepository.save(existingdni);
                });
    }

    /**
     * Eliminar un producto por su ID (eliminado lógico)
     * @param id ID del producto a eliminar
     * @return Mono<ProductoModel> producto con estado actualizado a "inactivo"
     */
    public Mono<ProductoModel> deleteLogicProducto(Long id) {
        return productoRepository.findById(id)
                .flatMap(producto -> {
                    // Cambiar el estado a "inactivo" en lugar de eliminarlo físicamente
                    producto.setEstado("inactivo");
                    return productoRepository.save(producto); // Guardar el cambio en la base de datos
                })
                .switchIfEmpty(Mono.error(new Exception("Producto no encontrado"))); // Si no se encuentra el producto
    }


    /**
     * Restaurar un producto (cambiar su estado a "activo").
     * 
     * @param id ID del producto a restaurar
     * @return Mono<ProductoModel> producto restaurado
     */
    public Mono<ProductoModel> restoreProducto(Long id) {
        return productoRepository.findById(id)
                .flatMap(producto -> {
                    // Verificar si el producto está inactivo
                    if ("inactivo".equalsIgnoreCase(producto.getEstado())) {
                        producto.setEstado("activo"); // Cambiar el estado a "activo"
                        return productoRepository.save(producto); // Guardar el producto restaurado
                    } else {
                        return Mono.error(new Exception("El producto no está inactivo, no se puede restaurar"));
                    }
                })
                .switchIfEmpty(Mono.error(new Exception("Producto no encontrado"))); // Si no se encuentra el producto
    }
}
