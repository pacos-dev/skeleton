---
name: skeleton-engineering-rules
description: Baseline rules for safe changes to the PacOS plugin skeleton.
---

# Skeleton Engineering Rules

Use this skill for every non-trivial skeleton change.

## Reference implementation

The skeleton demonstrates supported extension patterns and is intentionally small.

Prefer adapting an existing working example instead of inventing a new architecture.

Remove demonstration code that the target plugin does not need.

## Spring

Use constructor injection.

Keep plugin component scanning explicit through the plugin configuration package and SkeletonPackageScanning-style configuration.

Avoid broad scans that pull unrelated classes into the plugin context.

## Isolation

Keep plugin persistence isolated from PacOS core.

Keep authorization at plugin action boundaries.

Keep plugin state and resources owned by the plugin lifecycle.

## Versions

Keep Java, Spring Boot, Vaadin, and PacOS BOM versions aligned with the current skeleton POM.

Never copy versions from obsolete examples or documentation.

## Scope

Avoid unrelated dependency upgrades, formatting churn, generated frontend changes, and refactors.

