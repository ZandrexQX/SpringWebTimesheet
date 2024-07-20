package ru.gb.timesheet.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.model.Timesheet;
import ru.gb.timesheet.page.TimesheetPageDto;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimesheetPageService {

  private final TimesheetService timesheetService;
  private final ProjectService projectService;
  private final EmployeeService employeeService;

  public List<TimesheetPageDto> findAll() {
    return timesheetService.findAll().stream()
      .map(this::convert)
      .toList();
  }

  public Optional<TimesheetPageDto> findById(Long id) {
    return timesheetService.findById(id) // Optional<Timesheet>
      .map(this::convert);
  }

  private TimesheetPageDto convert(Timesheet timesheet) {
    Project project = projectService.findById(timesheet.getProjectId())
      .orElseThrow();
    Employee employee = employeeService.findById(timesheet.getEmployeeId())
            .orElseThrow();
    TimesheetPageDto timesheetPageParameters = new TimesheetPageDto();
    timesheetPageParameters.setProjectName(project.getName());
    timesheetPageParameters.setEmployeeName(employee.getName());
    timesheetPageParameters.setId(String.valueOf(timesheet.getId()));
    timesheetPageParameters.setMinutes(String.valueOf(timesheet.getMinutes()));
    timesheetPageParameters.setCreatedAt(timesheet.getCreatedAt().format(DateTimeFormatter.ISO_DATE));
    timesheetPageParameters.setProjectId(String.valueOf(timesheet.getProjectId()));

    return timesheetPageParameters;
  }

}
