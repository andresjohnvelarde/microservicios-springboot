package principalOrden;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OrdenServiceApplication {
    public static void main(String[] args){
        SpringApplication.run(OrdenServiceApplication.class,args);
    }
}
