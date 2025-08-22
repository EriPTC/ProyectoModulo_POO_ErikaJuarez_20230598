package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Autores;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AutoresDTO {

    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String apellido;

    @Nullable
    private String nacionalidad;

    @Past
    @Nullable
    private Date fecha_nacimiento;
}
