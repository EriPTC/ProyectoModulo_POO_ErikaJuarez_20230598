package ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Repositories.Autores;

import ErikaJuarez_2030598.ProyectoModulo_ErikaJuarez_2030598.Entities.Autores.AutoresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends JpaRepository<AutoresEntity, Long> {
}
