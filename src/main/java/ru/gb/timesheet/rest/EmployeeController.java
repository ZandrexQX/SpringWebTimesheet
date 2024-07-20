package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.EmployeeService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/employees")
@Tag(name = "Сотрудники", description = "API для сотрудников")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @Operation(summary = "Получить сотрудника", description = "Получить сотрудника по ID")
    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable @Parameter(description = "ID сотрудника") Long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Получить список затрат времени", description = "Получить список временных затрат по ID сотрудника")
    @GetMapping("/{id}/timesheets")
    public ResponseEntity<List<Timesheet>> getTimesheets(@PathVariable @Parameter(description = "ID сотрудника")  Long id) {
        try {
            return ResponseEntity.ok(service.getTimesheets(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Получить список сотрудников", description = "Получить весь список сотрудников")
    @GetMapping
    public ResponseEntity<List<Employee>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Создать сотрудника", description = "Создать нового сотрудника")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody @Parameter(description = "Новый сотрудник") Employee employee) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee));
    }

    @Operation(summary = "Удалить сотрудника", description = "Удалить сотрудника по ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Parameter(description = "ID сотрудника") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
