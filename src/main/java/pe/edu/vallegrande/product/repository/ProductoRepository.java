package pe.edu.vallegrande.product.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.product.model.ProductoModel;
import reactor.core.publisher.Flux;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<ProductoModel, Long> {

    // Método para obtener todos los productos por su campo 'descripcion'
    Flux<ProductoModel> findByDescripcion(String descripcion);
}
