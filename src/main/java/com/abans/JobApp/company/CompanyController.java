package com.abans.JobApp.company;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies(){
        return ResponseEntity.ok(companyService.getAllCompanies());
    }

    @PostMapping
    public ResponseEntity<String> postCompany(@RequestBody Company company){
        companyService.addCompany(company);
        return new ResponseEntity<>("Company Added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null) return ResponseEntity.ok(company);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatedCompany(@PathVariable Long id, @RequestBody Company updatedCompany){
        boolean updated = companyService.updateCompanyById(id, updatedCompany);
        if(updated) return ResponseEntity.ok("Company Updated Successfully!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean deleted = companyService.deleteCompanyById(id);
        if(deleted) return ResponseEntity.ok("Company Deleted Successfully!!");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
