package ru.gb.timesheet.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.timesheet.model.Project;
import ru.gb.timesheet.page.ProjectPageDto;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectPageService {

    private final ProjectService projectService;

    public Optional<ProjectPageDto> findById(Long id) {
        return projectService.findById(id)
            .map(this::convert);
    }

    private ProjectPageDto convert(Project project) {
        if (project == null) {
            return null;
        }
        ProjectPageDto projectPageDto = new ProjectPageDto();
        projectPageDto.setName(project.getName());
        projectPageDto.setId(String.valueOf(project.getId()));
        return projectPageDto;
    }
}
