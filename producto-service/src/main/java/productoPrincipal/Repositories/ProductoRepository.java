package productoPrincipal.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import productoPrincipal.Models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
