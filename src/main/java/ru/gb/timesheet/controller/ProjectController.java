package ru.gb.timesheet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.service.ProjectService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Project>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> get(@PathVariable Long id) {
        Optional<Project> ps = service.findById(id);

        if (ps.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(ps.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = service.create(project);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
