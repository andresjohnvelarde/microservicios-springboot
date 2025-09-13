NOTAS*
- El proyecto fue realizado en Windows, sobretodo la diferencia se nota en el segundo punto del examen, en el archivo logging/docker-compose.yaml:
    volumes:
      - ./filebeat.yml:/usr/share/filebeat/filebeat.yml
      - C:\Users\andre\Desktop\Curso-Springboot-Avanzado\19082025\producto-service\target\logs:/host-logs:ro
      - C:\Users\andre\Desktop\Curso-Springboot-Avanzado\19082025\orden-service\target\logs:/host-logs2:ro
      - C:\Users\andre\Desktop\Curso-Springboot-Avanzado\19082025\inventario-service\target\logs:/host-logs3:ro // Línea para añadir al Logging inventario service y ser posible su visualización en Kibana.

- Para el primer punto se creó la nueva clase ProductoEvento en commons, y el microservicio producto-service, aparte de ser Consumidor, ahora también es Producer y el microservicio inventario-service es consumidor,
de tal forma que ahora al crear un Producto, mediante kafka se puede ver en inventario-service el log producido en producto-service.

* Enlace para los archivos properties:
https://github.com/andresjohnvelarde/config-server-springboot/tree/main

  
