package principalcommons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoEvent {
    private Long productoId;
    private String nombre;
    private Double precio;
}
