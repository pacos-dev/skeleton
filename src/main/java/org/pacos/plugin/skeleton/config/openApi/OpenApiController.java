package org.pacos.plugin.skeleton.config.openApi;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import io.swagger.v3.oas.annotations.Hidden;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.pacos.plugin.skeleton.backend.api.ApiConst.API_PATH;

/**
 * Rest controller for exposing openApi documentation for this plugin.
 * Documentation is presented by swagger-ui on pacos and contains a collection of all documentation provided by a
 * current pacos plugins configuration.
 * After installation inside pacos, this controller will be available under /plugin/skeleton/v3 address
 * <p>
 * Documentation is served from physical file generated during integration-test phase.
 * Can be removed if plugin does not provide an API
 */
@RestController
@RequestMapping(API_PATH)
@Hidden
public class OpenApiController {

    /**
     * Usage of this entry point is hardcoded on pacos side and will be used to fetch documentation if available
     */
    @GetMapping
    public ResponseEntity<String> getOpenApiDocs() throws IOException {

        try (InputStream inputStream = getClass().getResourceAsStream("/v3/api-docs.json")) {
            if (inputStream == null) {
                return ResponseEntity.notFound().build();
            }

            String openApiJson = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(openApiJson);
        }
    }
}
