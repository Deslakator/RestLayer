package ru.desl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.desl.entity.Employee;

@RepositoryRestResource(collectionResourceRel = "employee", path = "employee")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByName(@Param("name") String name);

    Employee findEmployeeByAddressCityContaining(String city);

    @Query("SELECT e from Employee e INNER JOIN e.address a where a.city = ?1")
    Employee findEmployeeByAddressCityContainingByQuery(String city);

    @Query(value = "SELECT e.* from employee e, empl_address a where e.address_id = a.id and a.city = ?1", nativeQuery = true)
    Employee findEmployeeByAddressCityContainingByQueryNative(String city);

    @Query(value = "SELECT e.* from employee e, empl_address a where e.address_id = a.id and a.city = :city", nativeQuery = true)
    Employee findEmployeeByAddressCityContainingByQueryNativeWithNameParma(@Param("city") String city);
}
