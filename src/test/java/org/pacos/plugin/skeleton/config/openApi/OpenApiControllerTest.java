package org.pacos.plugin.skeleton.config.openApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OpenApiController.class)
public class OpenApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    static {
        try {
            System.setProperty("workingDir", Files.createTempDirectory("my-test").toAbsolutePath().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void whenCallGetOpenApiDocsThenReturnJson() throws Exception {
        String expectedJson = IOUtils.toString(
                new ClassPathResource("/v3/api-docs.json").getInputStream(),
                StandardCharsets.UTF_8
        );

        mockMvc.perform(get("/plugin/skeleton/v3/api-docs"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(expectedJson));
    }
}
