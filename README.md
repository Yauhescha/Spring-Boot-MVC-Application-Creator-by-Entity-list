# Spring Boot MVC Application Generator

A Java utility that generates Spring Data repositories, CRUD services, MVC controllers and Thymeleaf pages from compiled model classes. It uses reflection to inspect entity fields and Apache Velocity to render the templates.

## Generated output

For a model such as `Book`, the generator creates:

| Layer | Example output |
| --- | --- |
| Repository | `generated/repository/BookRepository.java` |
| Service | `generated/service/BookService.java` |
| Controller | `generated/controller/BookController.java` |
| Thymeleaf pages | `generated/templates/book.html`, `book-one.html`, `book-edit.html` |

The output root is `src/main/java/generated/`. Templates are under `src/main/resources/templates/`.

## Requirements

- JDK 11 and Maven.
- An IDE capable of importing a Maven project and running Java entry points.
- Lombok annotation processing enabled in the IDE.

The checked-in templates target the Spring Boot 2.7 / `javax.persistence` stack. They require adaptation for a Spring Boot 3+ application.

## Use the generator

1. Add model classes to `hescha.expectedapplication.model`, or update `classForGenerating` in `hescha.creator.Main` to a class in your model package.
2. Compile those classes with `mvn compile`.
3. Run **`hescha.creator.Main`** in your IDE with the repository root as the working directory.
4. Review the files generated under `src/main/java/generated/`.
5. Integrate them into the target application, including the corresponding models and shared CRUD base service.

Example model:

```java
package hescha.expectedapplication.model;

import javax.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Book extends AbstractEntity {
    private String title;
}
```

The repository's default model package contains the abstract base class only, so add concrete models before expecting generated output. `hescha.Starter` starts the sample Spring application; it is a different entry point from the generator.

## Customize and integrate

- `creator/ClassType.java` defines output names and template paths.
- `creator/CodeGenerator.java` renders a template for a class.
- `creator/Main.java` discovers compiled classes and writes generated files.
- The repository template derives query methods from declared fields and selected annotations.

Generated sources refer to `generated.model`; align the model package in the target application. Move generated HTML files into that application's `src/main/resources/templates/`. Re-running the generator overwrites files with matching generated names. Review relationship handling, validation and repository methods before using the result.
