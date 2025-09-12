package principalOrden.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import principalOrden.Models.Orden;
import principalOrden.Services.OrdenService;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> listarOrdenes(){
        return ordenService.getTodasLasOrdenes();
    }

    @PostMapping
    public Orden crearOrden(@RequestBody Orden ord){
        return ordenService.registrarOrden(ord);
    }
}
