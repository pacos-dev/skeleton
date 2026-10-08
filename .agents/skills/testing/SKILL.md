---
name: skeleton-testing
description: Preserve skeleton testing conventions and choose the right test level.
---

# Skeleton Testing

Use this skill whenever skeleton code changes.

## Test hierarchy

Prefer:

1. unit test
2. Spring context test
3. REST or MVC test
4. Vaadin or UI test
5. full integration test

Do not use a full SpringBootTest for behavior that can be proved in a unit test.

## Existing helpers

Reuse current Vaadin test setup, including VaadinMock when current-session or current-UI access is required.

Do not copy VaadinMock into production code. It is test infrastructure for keeping mocked Vaadin current instances reachable.

## OpenAPI test side effect

OpenAPIGeneratorTest retrieves plugin API documentation and writes generated JSON into target/classes/v3/api-docs.json and src/main/resources/v3/api-docs.json.

Treat that write as an intentional test side effect. Do not silently commit the generated resource.

## Test naming

Use method names in the form whenCallMethodThenExpectedResult.

Avoid comments inside tests. Express intent through names and assertions.

## Regression focus

Test extension contracts, permission allow and deny behavior where relevant, registration, and cleanup for lifecycle-sensitive code.

Run the narrowest relevant test first and report unexecuted tests honestly.

