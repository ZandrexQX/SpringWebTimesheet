package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/projects")
@Tag(name = "Проекты", description = "API для проектов")
public class ProjectController {

  private final ProjectService service;

  public ProjectController(ProjectService service) {
    this.service = service;
  }

  @Operation(summary = "Получить проект", description = "Получить проект по ID")
  @GetMapping("/{id}")
  public ResponseEntity<Project> get(@PathVariable @Parameter(description = "ID проекта") Long id) {
    return service.findById(id)
      .map(ResponseEntity::ok)
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @Operation(summary = "Получить список затрат времени", description = "Получить список временных затрат по ID проекта")
  @GetMapping("/{id}/timesheets")
  public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable @Parameter(description = "ID проекта") Long id) {
    try {
      return ResponseEntity.ok(service.getTimesheets(id));
    } catch (NoSuchElementException e) {
      return ResponseEntity.notFound().build();
    }
  }

  @Operation(summary = "Получить список проектов", description = "Получить весь список проектов")
  @GetMapping
  public ResponseEntity<List<Project>> getAll() {
    return ResponseEntity.ok(service.findAll());
  }

  @Operation(summary = "Создать проект", description = "Создать новый проект")
  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public ResponseEntity<Project> create(@RequestBody @Parameter(description = "Новый проект") Project project) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.create(project));
  }

  @Operation(summary = "Удалить проект", description = "Удалить проект по ID")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID проекта") Long id) {
    service.delete(id);
    return ResponseEntity.noContent().build();
  }

}
