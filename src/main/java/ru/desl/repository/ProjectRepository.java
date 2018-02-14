package ru.desl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.desl.entity.Project;

@RepositoryRestResource
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
