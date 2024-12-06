package pe.edu.vallegrande.dni.model;

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
@Table("consultas")
public class DniModelModel {

    @Id
    private Long id;

    @Column("success")
    private String success;

    @Column("dni")
    private String dni;

    @Column("dni")
    private String dni;

    @Column("apellidoPaterno")
    private String apellidoPaterno;

    @Column("apellidoMaterno")
    private String apellidoMaterno;

    @Column("codVerifica")
    private Integer codVerifica;
}
