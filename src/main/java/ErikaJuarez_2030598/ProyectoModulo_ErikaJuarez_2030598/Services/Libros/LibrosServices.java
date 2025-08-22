package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Services.Libros;

import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Entities.Libros.LibrosEntity;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions.ExcepcionLibroNoRegistrado;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions.ExceptionCamposVacios;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Exceptions.ExceptionLibroNoEncontrado;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Model.DTO.Libros.LibrosDTO;
import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Repositories.Libros.LibrosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LibrosServices {
    @Autowired
    LibrosRepository librosRepository;

    public List<LibrosDTO>obtenerLibros (){
        List<LibrosEntity> lista = librosRepository.findAll();
        return lista.stream()
                .map(this::ConvertirADTO)
                .collect(Collectors.toList());
    }

    private LibrosDTO ConvertirADTO(LibrosEntity entity){
        LibrosDTO dto = new LibrosDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setIsbn(entity.getIsbn());
        dto.setAño_publicacion(entity.getAño_publicacion());
        dto.setGenero(entity.getGenero());
        dto.setAutor_id(entity.getAutor_id());
        return dto;
    }

    private LibrosEntity ConvertirAEntity (LibrosDTO dto) {
        LibrosEntity entity = new LibrosEntity();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setIsbn(dto.getIsbn());
        entity.setAño_publicacion(dto.getAño_publicacion());
        entity.setGenero(dto.getGenero());
        entity.setAutor_id(dto.getAutor_id());
        return entity;
    }

    public LibrosDTO InsertarDatos (LibrosDTO data){
        if (data == null){
            throw new ExceptionCamposVacios("No se permiten campos vacios");
        }
        try {
            LibrosEntity entity = ConvertirAEntity(data);
            LibrosEntity LibroGuardado = librosRepository.save(entity);
            return ConvertirADTO(LibroGuardado);
        }catch (Exception e){
            log.error("No se ha podido registar el libro" +e.getMessage());
            throw new ExcepcionLibroNoRegistrado("No se ha podido registar el libro");
        }
    }

    public LibrosDTO ActualizarLibro (Long id, LibrosDTO json){
        LibrosEntity existente = librosRepository.findById(id).orElseThrow(()-> new ExceptionLibroNoEncontrado("No se ha encontrado el libro"));
        existente.setId(json.getId());
        existente.setIsbn(json.getIsbn());
        existente.setTitulo(json.getTitulo());
        existente.setAño_publicacion(json.getAño_publicacion());
        existente.setGenero(json.getGenero());
        existente.setAutor_id(json.getAutor_id());
        LibrosEntity LibroActualizado = librosRepository.save(existente);
        return ConvertirADTO(LibroActualizado);
    }

    public Boolean removerLibro (Long id){
        LibrosEntity existente = librosRepository.findById(id).orElse(null);
        try {
            if (existente != null){
                librosRepository.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("no se encontro el  libro con id:" +id+ "no se elimino el libro", 1);
        }
    }
}


