package org.pacos.plugin.skeleton.backend.api;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthCheckApiControllerTest {

    @Test
    public void whenCallIsAliveThenReturnTrue() {
        ResponseEntity<Boolean> alive = new HealthCheckApiController().isAlive();
        assertEquals(Boolean.TRUE, alive.getBody());
    }
}
