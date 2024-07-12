package ru.gb.timesheet.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Timesheet {

  private Long id;
  private Long projectId;
  private int minutes;
  private LocalDate createdAt;

  public Timesheet(Long id, Project project) {
    this.id = id;
    this.projectId = project.getId();
    this.createdAt = LocalDate.now();
  }

}
