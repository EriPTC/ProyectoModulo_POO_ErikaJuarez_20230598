package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Services.Libros;

import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Entities.Libros.LibrosEntity;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Libros.LibrosDTO;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Repositories.Libros.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Service
public class LibrosServices {
    @Autowired
    LibrosRepository librosRepository;

    public List<LibrosDTO>obtenerLibros (){
        List<LibrosEntity> lista = librosRepository.findAll();
        return lista.stream()
                .map(this::ConvertirADTO)
                .collect(collectors.toList());
    }

    private LibrosDTO convertirADTO(LibrosEntity entity){
        LibrosDTO dto = new LibrosDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAño_publicacion(entity.getAño_publicacion());

    }
}


