package productoPrincipal.Services;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    public boolean validarCredenciales(String usuario, String password){
        //Conexion a BD o fuente de datos
        return usuario.equals("Pepe") && password.equals("123ab");
    }
}
