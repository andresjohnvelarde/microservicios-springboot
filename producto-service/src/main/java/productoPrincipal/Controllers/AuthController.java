package productoPrincipal.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productoPrincipal.DTOs.JwtResponse;
import productoPrincipal.DTOs.LoginRequest;
import productoPrincipal.Services.JwtUtil;
import productoPrincipal.Services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request){
        if (usuarioService.validarCredenciales(request.getUsername(),request.getPassword())){
            String token = jwtUtil.generarToken(request.getUsername());
            return ResponseEntity.ok(new JwtResponse(token));
        }else {
            return ResponseEntity.status(401).body("Credenciales incorrectas");
        }
    }
}
