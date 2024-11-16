package pe.edu.vallegrande.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.product.model.ProductoModel;
import pe.edu.vallegrande.product.repository.ProductoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtener todos los productos activos
     * @return Flux<ProductoModel> lista de productos activos
     */
    public Flux<ProductoModel> getAllProductos() {
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
                    existingProducto.setTipo(producto.getTipo());
                    existingProducto.setDescripcion(producto.getDescripcion());
                    existingProducto.setPesoPaquete(producto.getPesoPaquete());
                    existingProducto.setCantidadPaquete(producto.getCantidadPaquete());
                    existingProducto.setPrecioKilo(producto.getPrecioKilo());
                    existingProducto.setStock(producto.getStock());
                    existingProducto.setFechaIngreso(producto.getFechaIngreso());
                    existingProducto.setFechaVencimiento(producto.getFechaVencimiento());
                    existingProducto.setEstado(producto.getEstado());
                    return productoRepository.save(existingProducto);
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
}
