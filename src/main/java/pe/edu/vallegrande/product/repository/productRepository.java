package pe.edu.vallegrande.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.vallegrande.model.ProductoModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductoRepository extends ReactiveCrudRepository<ProductoModel, Long> {

    // Método para obtener todos los productos por su campo 'nombres'
    Flux<ProductoModel> findByNombres(String nombres);

    // Método para obtener un producto por su ID
    Mono<ProductoModel> findById(Long id);
}
