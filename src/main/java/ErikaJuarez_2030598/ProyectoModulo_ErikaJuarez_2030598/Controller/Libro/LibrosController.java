package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Controller.Libro;


import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Libros.LibrosDTO;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Services.Libros.LibrosServices;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private ResponseEntity<Map<String, Objects>>AgregarLibro(@Valid @RequestBody LibrosDTO json, HttpServletRequest request){

        try {
            LibrosDTO response = services.InsertarDatos(json);
            if (response == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "Error", ""
                ));
            }
        }
    }
}
