package uz.pdp.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Company;
import uz.pdp.entity.Department;
import uz.pdp.payload.DepartmentDto;
import uz.pdp.repository.CompanyRepository;
import uz.pdp.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository;

    public ResponseEntity<?> getAllDepartment() {
        List<Department> departmentList = departmentRepository.findAll();
        return ResponseEntity.ok(departmentList);
    }

    public ResponseEntity<?> add(DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(optionalCompany.get());
        departmentRepository.save(department);
        return ResponseEntity.status(200).body("Department saved");
    }

    public ResponseEntity<?> edit(Integer id, DepartmentDto departmentDto) {
        Optional<Company> optionalCompany = companyRepository.findById(departmentDto.getCompanyId());
        if (!optionalCompany.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Company not found");
        Company company = optionalCompany.get();
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (!optionalDepartment.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Department not found");
        Department department = optionalDepartment.get();
        department.setName(department.getName());
        department.setCompany(company);
        departmentRepository.save(department);
        return ResponseEntity.status(202).body("Department edited");
    }
}
