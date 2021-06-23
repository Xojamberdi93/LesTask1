package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.payload.DepartmentDto;
import uz.pdp.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return departmentService.getAllDepartment();
    }
   @PostMapping
    public ResponseEntity<?> addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.add(departmentDto);
   }
   @PutMapping("/{id}")
  public ResponseEntity<?> editDepartment(@PathVariable Integer id,@RequestBody DepartmentDto departmentDto){
        return departmentService.edit(id,departmentDto);
   }
}
