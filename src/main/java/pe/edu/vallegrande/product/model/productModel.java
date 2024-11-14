package pe.edu.vallegrande.model;

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

    @Column("fecha")
    private LocalDate fecha;

    @Column("nombres")
    private String nombres;

    @Column("rc")
    private String rc;

    @Column("direccion")
    private String direccion;

    @Column("total_paquetes")
    private Integer totalPaquetes;

    @Column("peso_total")
    private BigDecimal pesoTotal;

    @Column("precio_x_kilo")
    private BigDecimal precioXKilo;

    @Column("igv")
    private BigDecimal igv;

    @Column("total_a_pagar")
    private BigDecimal totalAPagar;
}
