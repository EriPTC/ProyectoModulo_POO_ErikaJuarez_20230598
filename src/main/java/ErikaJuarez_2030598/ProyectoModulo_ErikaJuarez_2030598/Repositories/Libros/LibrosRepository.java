package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Repositories.Libros;

import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Entities.Libros.LibrosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrosRepository extends JpaRepository<LibrosEntity, Long> {
}
