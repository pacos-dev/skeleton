# Skeleton app

This project is a reference skeleton for building Java extensions that run inside the PacOS system.

The current skeleton targets **Java 21**, **Spring Boot 4.1.1**, **Vaadin 25.3.0**, and **PacOS BOM 3.4.0**. The versions should stay aligned with the PacOS release that will load the plugin.

## Developer workflow

The skeleton is both:

- a runnable local PacOS environment for extension development
- a packaging template for installing the resulting shaded JAR into an existing PacOS instance

Run the local application from `org.pacos.plugin.skeleton.Skeleton`.

The first run starts the PacOS installation flow. After installation, open:

`http://localhost:8086/desktop`

To change the PacOS working directory, add:

`-DworkingDir=/path/to/dir`

## Spring configuration

PacOS scans the plugin configuration entry point:

`org.pacos.plugin.<your.module.name>.config`

The skeleton contains `SkeletonPackageScanning`, which explicitly selects the plugin backend and UI configuration packages.

Use constructor injection for Spring dependencies.

## Project structure

```text
org.pacos.plugin.skeleton
├── config       Spring, database, Flyway and OpenAPI integration
├── backend      Services, persistence, REST and variable providers
├── security     Plugin permissions
├── system       Plugin-local events and system coordination
└── view         PacOS desktop windows and settings UI
```

Remove example packages that your plugin does not need.

## Supported extension points

The skeleton demonstrates the current PacOS extension model:

- `WindowConfig` + prototype `DesktopWindow` for desktop applications
- `SettingTab` for settings pages
- `Permission` for plugin actions and access rules
- `VariableProvider` for plugin variables and scopes
- `PluginListener` for plugin lifecycle events
- `ExecutableBlock` for automation integrations
- Spring MVC controllers and OpenAPI for REST APIs
- Vaadin `RequestHandler` when custom resource handling is required

See the PacOS developer documentation for the contract of each extension.

## Static resources

Place plugin resources under:

`src/main/resources/META-INF/resources/`

The skeleton also copies frontend source assets into the generated `META-INF/resources/frontend` directory during the Maven build.

## Database

The skeleton uses an independent plugin datasource, JPA entity manager and transaction manager.

Flyway migrations are stored under:

`src/main/resources/db/migration/skeleton`

Use a unique property prefix, persistence-unit name and migration location for a real plugin.

## API documentation

The skeleton exposes a sample REST API under the plugin namespace:

`/plugin/skeleton/alive`

OpenAPI documentation is generated for the sample API and is bundled into the plugin as:

`/v3/api-docs.json`

PacOS consumes that document when aggregating plugin API documentation.

## Run

For local development, run `org.pacos.plugin.skeleton.Skeleton`.

The skeleton is configured to use the Jetty Maven plugin for local execution on port `8099`.

HotSwap documentation:

https://vaadin.com/docs/latest/configuration/live-reload/hotswap-agent

## Package

Create an installable shaded JAR with:

```bash
mvn clean package
```

The artifact can then be installed through PacOS plugin management or provided by a configured Maven-compatible repository.

## Frontend dependencies

The Maven build runs the Vaadin frontend preparation step. Generated frontend files are intentionally ignored by Git and should not be committed.

## Useful links

- Vaadin components: https://vaadin.com/docs/latest/components
- PacOS documentation: https://pacos.dev
- PacOS core: https://github.com/pacos-dev/pacos
- PacOS plugin skeleton: https://github.com/pacos-dev/skeleton
