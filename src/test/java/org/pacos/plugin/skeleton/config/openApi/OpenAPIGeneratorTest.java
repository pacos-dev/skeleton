package org.pacos.plugin.skeleton.config.openApi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.pacos.config.property.WorkingDir;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import static org.pacos.plugin.skeleton.backend.api.ApiConst.PLUGIN_NAME;

/**
 * This test is responsible for saving the current API documentation to a physical file inside the package being built
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OpenAPIGeneratorTest {

    private final RestTemplate restTemplate = new RestTemplate();

    static {
        WorkingDir.initialize();
    }

    @LocalServerPort
    private int port;

    @Test
    public void generateOpenApiDocumentation() throws IOException {
        String openApiUrl = "http://localhost:" + port + "/v3/api-docs/" + PLUGIN_NAME;

        String json = restTemplate.getForObject(openApiUrl, String.class);

        File outputFile = new File("target/classes/v3/api-docs.json");
        if (!outputFile.getParentFile().exists()) {
            outputFile.getParentFile().mkdirs();
        }

        try (FileOutputStream outputStream = new FileOutputStream(outputFile)) {
            FileCopyUtils.copy(json.getBytes(), outputStream);
        }

        File outputFile2 = new File("src/main/resources/v3/api-docs.json");
        if (!outputFile2.getParentFile().exists()) {
            outputFile2.getParentFile().mkdirs();
        }

        try (FileOutputStream outputStream = new FileOutputStream(outputFile2)) {
            FileCopyUtils.copy(json.getBytes(), outputStream);
        }
    }
}