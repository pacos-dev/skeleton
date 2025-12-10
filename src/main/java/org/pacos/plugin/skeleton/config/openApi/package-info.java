/**
 * Provides basic configuration for generating API documentation
 * In dev mode, documentation will be exposed at "/v3/api-docs" endpoint. During the integration testing phase,
 * the documentation is exported and saved as a physical file inside the compiled package.
 * In the prod mode when module is installed as a pacos extension, documentation will be provided by the
 * OpenApiController from the prepared file
 */
package org.pacos.plugin.skeleton.config.openApi;