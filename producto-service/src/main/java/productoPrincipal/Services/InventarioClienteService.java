package productoPrincipal.Services;

import com.clase2.inventario.grpc.InventarioServiceGrpc;
import com.clase2.inventario.grpc.StockRequest;
import com.clase2.inventario.grpc.StockResponse;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InventarioClienteService {
    private static final Logger logger = LoggerFactory.getLogger(InventarioClienteService.class);
    @GrpcClient("inventario-service")
    private InventarioServiceGrpc.InventarioServiceBlockingStub inventarioStub;
    public int obtenerStockPorGrpc(Long productoId){
        StockRequest request = StockRequest.newBuilder().setProductoid(productoId).build();
        StockResponse response = inventarioStub.getStock(request);
        logger.info("Enviando petición al servidor Grpc con productoid: {}", productoId);
        return response.getCantidad();
    }
}
