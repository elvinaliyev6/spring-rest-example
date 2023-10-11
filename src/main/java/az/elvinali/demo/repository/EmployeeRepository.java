package az.elvinali.demo.repository;

import az.elvinali.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByIdAndActive(Long id,Integer active);
    List<Employee> findAllByActive(Integer active);
}
