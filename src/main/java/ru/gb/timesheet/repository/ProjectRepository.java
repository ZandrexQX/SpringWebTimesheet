package ru.gb.timesheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.timesheet.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
