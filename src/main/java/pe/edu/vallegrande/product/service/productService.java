package pe.edu.vallegrande.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.vallegrande.model.ProductoModel;
import pe.edu.vallegrande.repository.ProductoRepository;
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
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Mono<ProductoModel> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    // Obtener productos por nombre
    public Flux<ProductoModel> getProductosByNombres(String nombres) {
        return productoRepository.findByNombres(nombres);
    }

    // Crear un nuevo producto
    public Mono<ProductoModel> createProducto(ProductoModel producto) {
        return productoRepository.save(producto);
    }

    // Actualizar un producto existente
    public Mono<ProductoModel> updateProducto(Long id, ProductoModel producto) {
        return productoRepository.findById(id)
                .flatMap(existingProducto -> {
                    existingProducto.setFecha(producto.getFecha());
                    existingProducto.setNombres(producto.getNombres());
                    existingProducto.setRc(producto.getRc());
                    existingProducto.setDireccion(producto.getDireccion());
                    existingProducto.setTotalPaquetes(producto.getTotalPaquetes());
                    existingProducto.setPesoTotal(producto.getPesoTotal());
                    existingProducto.setPrecioXKilo(producto.getPrecioXKilo());
                    existingProducto.setIgv(producto.getIgv());
                    existingProducto.setTotalAPagar(producto.getTotalAPagar());
                    return productoRepository.save(existingProducto);
                });
    }

    // Eliminar un producto por ID
    public Mono<Void> deleteProducto(Long id) {
        return productoRepository.deleteById(id);
    }
}
