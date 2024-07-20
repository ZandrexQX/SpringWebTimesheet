package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Employee;
import ru.gb.timesheet.model.Timesheet;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
