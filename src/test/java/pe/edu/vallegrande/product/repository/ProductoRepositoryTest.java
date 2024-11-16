package pe.edu.vallegrande.product.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.test.context.ActiveProfiles;
import pe.edu.vallegrande.product.model.ProductoModel;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;

@DataR2dbcTest
@ActiveProfiles("test") // Usa un perfil de pruebas
public class ProductoRepositoryTest {

    @Autowired
    private ProductoRepository productoRepository;

    @Test
    void testFindByDescripcionIgnoreCase() {
        // Arrange
        String descripcionOriginal = "Producto Test";
        String descripcionBuscar = "producto test";
        ProductoModel producto = new ProductoModel(
                null, 
                "venta", 
                descripcionOriginal, 
                new BigDecimal("10.0"), 
                20, 
                new BigDecimal("50.0"), 
                100, 
                LocalDate.now(), 
                LocalDate.now().plusMonths(1), 
                "activo"
        );

        // Guardar el producto en la base de datos
        productoRepository.save(producto).block();

        // Act
        Flux<ProductoModel> productos = productoRepository.findByDescripcionIgnoreCase(descripcionBuscar);

        // Assert
        StepVerifier.create(productos)
                .expectNextMatches(p -> p.getDescripcion().equalsIgnoreCase(descripcionOriginal))
                .verifyComplete();
    }
}
