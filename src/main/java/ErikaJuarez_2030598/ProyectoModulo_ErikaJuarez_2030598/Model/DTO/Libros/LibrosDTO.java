package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Libros;


import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.Date;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LibrosDTO {


    private Long id;

    @NotNull
    private String titulo;

    @NotNull @UniqueElements
    private String isbn;

    @Positive @Nullable
    private  int año_publicacion;

    @Nullable
    private String genero;

    @NotNull
    private Long autor_id;


}
