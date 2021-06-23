package uz.pdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {

}
