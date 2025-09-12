package com.clase2.inventario.grpc;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class InventarioGrpcServiceImpl extends InventarioServiceGrpc.InventarioServiceImplBase{
    private static final Logger logger = LoggerFactory.getLogger(InventarioGrpcServiceImpl.class);
    @Override
    public void getStock(StockRequest request, StreamObserver<StockResponse> responseObserver){
        int cant = 33; // Acá viene la lógica de negocio
        StockResponse response = StockResponse.newBuilder().setCantidad(cant).build();
        logger.info("Enviando respuesta al cliente Grpc con stock: {}", cant);
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
