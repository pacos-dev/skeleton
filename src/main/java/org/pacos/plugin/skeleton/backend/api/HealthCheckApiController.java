package org.pacos.plugin.skeleton.backend.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.pacos.plugin.skeleton.backend.api.ApiConst.ALIVE_PATH;

/**
 * Example rest controller
 * Remark: After installation inside pacos, this controller will be available under /plugin/skeleton/alive address
 */
@RestController
@RequestMapping(path = ALIVE_PATH)
@Tag(name = "Health check controller", description = "Check if plugin is alive")
public class HealthCheckApiController {

    @Operation(operationId = "isAlive", description = "Return true if alive")
    @GetMapping
    public ResponseEntity<Boolean> isAlive() {
        return ResponseEntity.ok(true);
    }
}
