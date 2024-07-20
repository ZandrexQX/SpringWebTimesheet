package ru.gb.timesheet.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Пример работы запросов", description = "API для примера")
public class HelloController {

  // GET http://localhost:8080/hello?username=Igor
  @Operation(summary = "Пример работы GET запроса")
  @GetMapping("/hello")
  public String helloPage(@RequestParam String username) {
    return "<h1>Hello, " + username + "!</h1>";
  }

  // GET http://localhost:8080/hello/igor
  // GET http://localhost:8080/hello/alex
  @Operation(summary = "Пример работы GET запроса с параметром")
  @GetMapping("/hello/{username}")
  public String helloPagePathVariable(@PathVariable String username) {
    return "<h1>Hello, " + username + "!</h1>";
  }

}
