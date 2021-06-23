package uz.pdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.entity.Company;
import uz.pdp.payload.CompanyDto;
import uz.pdp.service.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        ResponseEntity<List<Company>> companies = companyService.Companies();
        return companies;
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Integer id){
        return companyService.getCompamy(id);
    }
    @PostMapping
    public ResponseEntity<?> addCompany(@RequestBody CompanyDto companyDto){
        return companyService.addCompany(companyDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        return companyService.edit(id,companyDto);
    }
}
