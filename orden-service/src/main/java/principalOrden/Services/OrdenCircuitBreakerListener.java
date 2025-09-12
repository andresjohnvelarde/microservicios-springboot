package principalOrden.Services;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.event.CircuitBreakerOnStateTransitionEvent;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class OrdenCircuitBreakerListener { // También se puede usar RequiredArgsConstructor
    private final CircuitBreakerRegistry circuitBreakerRegistry;
    private final OrdenRecoveryService ordenRecoveryService;

    public OrdenCircuitBreakerListener(CircuitBreakerRegistry circuitBreakerRegistry, OrdenRecoveryService ordenRecoveryService) {
        this.circuitBreakerRegistry = circuitBreakerRegistry;
        this.ordenRecoveryService = ordenRecoveryService;
    }

    @PostConstruct
    public void registerCircuitBreakerEventListener(){
        CircuitBreaker circuitBreaker = circuitBreakerRegistry.circuitBreaker("productoServiceCB");
        circuitBreaker.getEventPublisher().onStateTransition(this::manejarEstadoDeTransmision);
    }

    private void manejarEstadoDeTransmision(CircuitBreakerOnStateTransitionEvent event){
        System.out.println("El estado es ahora... "+event.getStateTransition());
        if(event.getStateTransition().getToState() == CircuitBreaker.State.CLOSED) {
            System.out.println("Se procede con la recuperación de ordenes pendientes");
            ordenRecoveryService.recuperacionOrdenesPendientes();
        }
    }
}
