package pe.edu.vallegrande.product.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("producto")
public class ProductoModel {

    @Id
    private Long id;

    @Column("tipo")
    private String tipo; // "venta" o "casa" (consumo interno)

    @Column("descripcion")
    private String descripcion;

    @Column("peso_paquete")
    private BigDecimal pesoPaquete;

    @Column("cantidad_paquete")
    private Integer cantidadPaquete;

    @Column("precio_kilo")
    private BigDecimal precioKilo;

    @Column("stock")
    private Integer stock;

    @Column("fecha_ingreso")
    private LocalDate fechaIngreso;

    @Column("fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column("estado")
    private String estado; // "activo" o "inactivo"
}
