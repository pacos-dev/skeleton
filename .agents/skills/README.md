# Skeleton AI Agent Skills

Repository-local guidance for AI coding agents working on the PacOS plugin skeleton.

The skeleton is a reference implementation and development launcher. The PacOS core source is the authority for extension contracts.

## Selection

- New plugin feature: engineering-rules, plugin-development, extension-patterns, testing, commit-review
- UI or settings: plugin-development, extension-patterns, testing, packaging-and-runtime
- REST or persistence: extension-patterns, testing, packaging-and-runtime
- Packaging: packaging-and-runtime, commit-review
- Local runtime: packaging-and-runtime, testing

When a change touches a PacOS contract, inspect the current core implementation before coding.

## General rule

Copy the shape of the closest working skeleton example, adapt it to the plugin domain, and remove capabilities that are not needed.

Keep plugin-owned state, configuration, persistence, and authorization isolated from PacOS core.

