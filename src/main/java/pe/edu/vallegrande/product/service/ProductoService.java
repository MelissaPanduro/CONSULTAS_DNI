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

    // Obtener todos los productos
    public Flux<ProductoModel> getAllProductos() {
        return productoRepository.findAll(); // Esto devuelve todos los productos
    }

    // Obtener un producto por ID
    public Mono<ProductoModel> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Crear un nuevo producto
    public Mono<ProductoModel> createProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Mono<ProductoModel> updateProducto(Long id, ProductoModel producto) {
        return productoRepository.findById(id)
                .flatMap(existingProducto -> {
                    // Actualiza los campos correspondientes
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

    // Eliminar un producto por ID
    public Mono<Void> deleteProducto(Long id) {
        return productoRepository.deleteById(id);
    }
}
