package principalcommons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenEvent {
    private Long ordenId;
    private Long productoId;
    private Integer cantidad;
}
