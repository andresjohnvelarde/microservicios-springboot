package principalOrden.Configs;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import principalOrden.DTOs.Producto;
@FeignClient(name = "producto-service")
public interface ProductoClient {
    @GetMapping("/producto/{id}")
    Producto obtenerProducto(@PathVariable("id") Long id);
}
