package principalOrden.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import principalOrden.Models.Orden;

import java.util.List;

public interface OrdenRepository extends JpaRepository<Orden,Long> {
    List<Orden> findByTotal(Double total);

    @Query("SELECT o FROM Orden o WHERE o.total = -1.0")
    List <Orden> obtenerRegistrosPendientes();
}
