package ru.desl.domain;
/*
 * Created by DeSlakator on 13.02.2018.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.desl.entity.Address;
import ru.desl.entity.Employee;
import ru.desl.entity.Project;
import ru.desl.repository.AddressRepository;
import ru.desl.repository.EmployeeRepository;
import ru.desl.repository.ProjectRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class Domain {

    private final AddressRepository addressRepository;
    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public Domain(AddressRepository addressRepository, EmployeeRepository employeeRepository, ProjectRepository projectRepository) {
        this.addressRepository = addressRepository;
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public void start() {
        Address melody = new Address();
        melody.setCity("Голубое, Мелодия Леса");
        melody.setPostcode("122549");

        //addressService.add(address); -- Т.к. включен режим каскада CascadeType.ALL

        Employee employeeDaniil = new Employee();
        employeeDaniil.setName("Daniil Lukianov");
        employeeDaniil.setAddress(melody);
        employeeRepository.save(employeeDaniil);

        Project projectJDBC = new Project();
        projectJDBC.setTitle("JDBC project");
        projectRepository.save(projectJDBC);

        Project projectHibernate = new Project();
        projectHibernate.setTitle("Hibernate project");
        projectRepository.save(projectHibernate);

        Set<Project> projects = new HashSet<>();
        projects.add(projectHibernate);
        employeeDaniil.setProjects(projects);
        employeeRepository.save(employeeDaniil);

        Address address = new Address();
        address.setCity("Зеленоград");
        address.setPostcode("112233");

        addressRepository.save(address);

        System.out.println("Address with new id: "+address);

        addressRepository.findAll().forEach(System.out::println);

        Address paris = new Address();
        paris.setCity("Париж");
        paris.setPostcode("z1235512");
        Employee employee = new Employee();
        employee.setName("France");
        employee.setAddress(paris);
        employeeRepository.save(employee);

        System.out.println(addressRepository.findById(1L));
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println();


        Address golyboe = new Address();
        golyboe.setCity("Киржач");
        golyboe.setPostcode("123432");
        //addressRepository.save(golyboe);

        Employee employee2 = new Employee();
        employee2.setName("Daniil");
        employee2.setAddress(golyboe);

        employeeRepository.save(employee2);

        System.out.println();
        System.out.println("-------------------------ALL---------------------------");
        System.out.println();
        employeeRepository.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println("-------------------------findByName---------------------------");
        System.out.println();
        System.out.println(employeeRepository.findByName("Daniil"));

        System.out.println();
        System.out.println("-------------------------findEmployeeByAddressCityContaining---------------------------");
        System.out.println();
        System.out.println(employeeRepository.findByName("Daniil"));
        System.out.println(employeeRepository.findEmployeeByAddressCityContaining("Голубое"));

        System.out.println();
        System.out.println("-------------------------ByQuery---------------------------");
        System.out.println("SELECT e from Employee e INNER JOIN e.address a where a.city = ?1");
        System.out.println();
        System.out.println(employeeRepository.findEmployeeByAddressCityContainingByQuery("Голубое"));

        System.out.println();
        System.out.println("-------------------------ByQueryNative---------------------------");
        System.out.println("SELECT e.* from employee e, empl_address a where e.address_id = a.id and a.city = ?1");
        System.out.println();
        System.out.println(employeeRepository.findEmployeeByAddressCityContainingByQueryNative("Голубое"));

        System.out.println();
        System.out.println("-------------------------ByQueryNativeWithNameParam---------------------------");
        System.out.println("SELECT e.* from employee e, empl_address a where e.address_id = a.id and a.city = :city");
        System.out.println();
        System.out.println(employeeRepository.findEmployeeByAddressCityContainingByQueryNativeWithNameParma("Голубое"));

    }


}
