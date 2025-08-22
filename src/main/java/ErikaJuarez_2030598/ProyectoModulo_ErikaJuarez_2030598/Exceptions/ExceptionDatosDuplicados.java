package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions;

import lombok.Getter;

public class ExceptionDatosDuplicados extends RuntimeException {
    public ExceptionDatosDuplicados(String message) {
        super(message);
    }
    @Getter
    private String campoDuplicado;


}
