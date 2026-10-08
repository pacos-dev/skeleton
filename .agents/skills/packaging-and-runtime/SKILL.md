---
name: skeleton-packaging-and-runtime
description: Validate skeleton Maven packaging, manifest, frontend preparation, and local runtime modes.
---

# Skeleton Packaging and Runtime

Use this skill for Maven, packaging, manifests, frontend assets, local launch, or runtime verification.

## Build contract

The skeleton uses the current PacOS dependency line configured in its POM.

Platform dependencies are generally provided because the host PacOS supplies them.

The package phase produces a shaded plugin JAR with required additional dependencies.

Do not change provided dependencies to compile or runtime scope without checking host runtime implications.

## Manifest

PacOS consumes plugin manifest metadata including implementation vendor, name, icon, version, group, and artifact metadata.

Keep required manifest values valid when changing Maven coordinates or packaging.

## Frontend

Vaadin frontend preparation is part of the build.

Keep source assets in intended resource locations and do not commit generated frontend output.

## Runtime modes

Skeleton.main starts a local PacOS environment on the platform default application port and exposes the desktop route at /desktop.

Maven Jetty execution uses the configured local connector on port 8099 and the desktop route remains /desktop unless configuration changes it.

Do not confuse the development launcher with the installable plugin artifact.

## Packaging validation

For packaging changes check Maven coordinates, BOM alignment, dependency scopes, shade configuration, manifest entries, frontend preparation, JAR contents, and runtime behavior.

