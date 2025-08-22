package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Controller.Libro;


import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions.ExceptionDatosDuplicados;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions.ExceptionLibroNoEncontrado;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Libros.LibrosDTO;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Services.Libros.LibrosServices;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.naming.Binding;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/LibrosApi")
public class LibrosController {

    @Autowired
    LibrosServices services;

    @GetMapping("/obtenerLibro")
    public List<LibrosDTO>obtenerLibro(){
        return services.obtenerLibros();
    }

    @PostMapping("/ingresarLibro")
    private ResponseEntity<Map<String, Object>>AgregarLibro(@Valid @RequestBody LibrosDTO json, HttpServletRequest request){

        try {
            LibrosDTO response = services.InsertarDatos(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Status", "Inserción incorrecta",
                        "Error", "VALIDATION_ERROR",
                        "Message", "Datos del usuario invalidos"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "Estado", "Completado",
                    "data", response
            ));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Status", "Error",
                    "message", "INTERNAL_SERVER_ERROR",
                    "deatil", e.getMessage()
            ));
        }
    }

    @PutMapping("/ActualizarLibro/{id}")
    public ResponseEntity<?> ActualizarLibro(@PathVariable Long id, @Valid @RequestBody LibrosDTO json, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            Map<String, String>errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(Error -> errores.put(Error.getField(), Error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);

        }
        try {
            LibrosDTO dto = services.ActualizarLibro(id, json);
            return ResponseEntity.ok(dto);

        }catch (ExceptionLibroNoEncontrado e){
            return ResponseEntity.notFound().build();
        }catch (ExceptionDatosDuplicados e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "Error", "Datos Duplicados",
                    "Campo", e.getCampoDuplicado()
            ));

        }
    }

    @DeleteMapping("/eliminarLibro/{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable Long id){
        try {
            if (!services.removerLibro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header("Mesaje Error", "Libro no encontrado").body(Map.of(
                   "Error", "Not Found",
                   "message", "El libro no ha sido encontrado",
                   "timestamp", Instant.now().toString()
                ));

            }
            return ResponseEntity.ok().body(Map.of(
                    "Status", "Proceso completado",
                    "message", "Libro eliminado exitosamente"
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status",  "Error",
                    "message", "Error no controlado",
                    "detail", e.getMessage()
            ));

        }
    }

}
