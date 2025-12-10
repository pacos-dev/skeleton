package org.pacos.plugin.skeleton.backend.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiConstTest {

    @Test
    public void whenGetPluginNameThenReturnSkeleton() {
        assertEquals("skeleton", ApiConst.PLUGIN_NAME);
    }

    @Test
    public void whenGetApiVersionThenReturn1_0_0() {
        assertEquals("1.0.0", ApiConst.API_VERSION);
    }

    @Test
    public void whenGetBasePathThenReturnPluginSkeleton() {
        assertEquals("plugin/skeleton/", ApiConst.BASE_PATH);
    }

    @Test
    public void whenGetApiPathThenReturnCorrectPath() {
        assertEquals("plugin/skeleton/v3/api-docs", ApiConst.API_PATH);
    }

    @Test
    public void whenGetAlivePathThenReturnCorrectPath() {
        assertEquals("plugin/skeleton/alive", ApiConst.ALIVE_PATH);
    }
}
