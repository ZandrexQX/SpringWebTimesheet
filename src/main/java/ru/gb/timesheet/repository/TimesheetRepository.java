package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.gb.timesheet.model.Timesheet;

import java.time.LocalDate;
import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long>{
  @Query("select t from Timesheet t where t.projectId = :projectId order by t.createdAt desc")
  List<Timesheet> findByProjectId(Long projectId);

  List<Timesheet> findByEmployeeId(Long employeeId);

  @Query("select t from Timesheet t where t.createdAt between :from and :to order by t.createdAt desc")
  List<Timesheet> findByCreatedAtBetween(LocalDate from, LocalDate to);
}
