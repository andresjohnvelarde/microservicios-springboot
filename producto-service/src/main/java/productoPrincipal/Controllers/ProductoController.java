package productoPrincipal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import productoPrincipal.Models.Producto;
import productoPrincipal.Services.InventarioClienteService;
import productoPrincipal.Services.ProductoService;

import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private ProductoService prodService;
    @Autowired
    private InventarioClienteService inventarioClienteService;

    @GetMapping
    public List<Producto> getAll(){
        return prodService.getAll();
    }

    @GetMapping("/{id}")
    public Producto getProducto(@PathVariable("id") Long id){
        return  prodService.getById(id);
    }

    @PostMapping
    public Producto crear(@RequestBody Producto prod){
        return prodService.registrar(prod);
    }

    @GetMapping("/stock/{id}") // http://localhost:8093/producto/stock/1 [GET]
    public int getProductoStock(@PathVariable Long id){
        return inventarioClienteService.obtenerStockPorGrpc(id);
    }
}
